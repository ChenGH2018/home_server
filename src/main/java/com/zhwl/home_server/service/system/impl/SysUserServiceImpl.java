package com.zhwl.home_server.service.system.impl;

import com.zhwl.home_server.bean.Page;
import com.zhwl.home_server.bean.shop.ShopBasic;
import com.zhwl.home_server.bean.system.SysUser;
import com.zhwl.home_server.bean.system.SysUserRole;
import com.zhwl.home_server.exception.BaseException;
import com.zhwl.home_server.mapper.system.sysuser.SysUserMapper;
import com.zhwl.home_server.service.shop.ShopBasicService;
import com.zhwl.home_server.service.system.SysUserRoleService;
import com.zhwl.home_server.service.system.SysUserService;
import com.zhwl.home_server.system.SysEnum;
import com.zhwl.home_server.system.UserTypeEnum;
import com.zhwl.home_server.util.SysUserUtil;
import com.zhwl.home_server.util.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service("sysUserServiceImpl")
@Transactional
public class SysUserServiceImpl implements SysUserService {

    @Resource(name = "sysUserMapper")
    private SysUserMapper sysUserMapper;
    @Resource(name = "sysUserRoleServiceImpl")
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private ShopBasicService shopBasicService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = Optional.ofNullable(username).map(x -> sysUserMapper.loadUserByUsername(x)).orElse(null);
        if (sysUser == null)
            throw new UsernameNotFoundException("用户名错误");
        if(sysUser.getUserType() == UserTypeEnum.SHOPUSER.getType()){
            ShopBasic shopBasic = new ShopBasic();
            shopBasic.setSysUserId(sysUser.getId());
            sysUser.setShopBasic(shopBasicService.selectBySelective(shopBasic).get(0));
        }
        return sysUser;
    }

    @Override
    public List<SysUser> selectAll() {
        return sysUserMapper.selectBySelective(null);
    }

    @Override
    public Page selectByPage(Page pg) {
        pg.setData(sysUserMapper.selectByPage(pg));
        return pg;
    }


    @Override
    public SysUser selectById(String id) {
        return Optional.ofNullable(id).map(x -> {
            SysUser sysUser = new SysUser();
            sysUser.setId(x);
            List<SysUser> list = sysUserMapper.selectBySelective(sysUser);
            return list != null && list.size() == 1 ? list.get(0) : null;
        }).orElse(null);
    }

    @Override
    public Integer save(SysUser sysUser) {
        //验证用户名和密码
        checkUsername(sysUser.getUsername());
        checkPassword(sysUser.getPassword());

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        sysUser.setPassword(encoder.encode(sysUser.getPassword()));
        sysUser.setUserface("http://bpic.588ku.com/element_pic/01/40/00/64573ce2edc0728.jpg");
        sysUser.setAddTime(new Date());
        //如果没有usertype则默认是系统用户（真正操作还会校验权限，所以没事）
        if(null == sysUser.getUserType()) sysUser.setUserType(UserTypeEnum.SYSTEMUSER.getType());
        sysUser.setIsFreeze(0);
        sysUser.setIsLogout(0);
        sysUser.setIsActivateEamil(0);
        return sysUserMapper.save(sysUser);
    }

    @Override
    public Integer updateBySelective(SysUser sysUser) {
        String username = sysUser.getUsername(); //用户名
        String password = sysUser.getPassword(); //密码

        SysUser su = this.selectById(sysUser.getId());
        Optional.ofNullable(su).ifPresent(x -> {
            if (username != null && !x.getUsername().equals(username)) {//当用户名改变时
                checkUsername(username);
            }
            if (password != null) {//当密码改变时
                checkPassword(password);
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                sysUser.setPassword(encoder.encode(sysUser.getPassword()));
            }
        });
        return sysUserMapper.updateBySelective(sysUser);
    }

    @Override
    public Integer updateSysUser(SysUser sysUser) {
        String userId = SysUserUtil.getCurrentUser().getId();
        if (userId == null || !sysUser.getId().equals(userId))
            throw new BaseException(0, "无权限修改信息");
        return updateBySelective(sysUser);
    }

    @Override
    public List<SysUser> selectBySelective(SysUser sysUser) {
        return sysUserMapper.selectBySelective(sysUser);
    }

    @Override
    public boolean checkPhoneExist(String phone) {
        return sysUserMapper.checkPhoneExist(phone) > 0;
    }

    @Override
    public boolean checkEmailExist(String email) {
        return sysUserMapper.checkEmailExist(email)>0;
    }

    //检查用户名
    private void checkUsername(String username) {
        if (StringUtils.isEmpty(username))
            throw new BaseException(SysEnum.USERNAMENULL);
        if (sysUserMapper.loadUserByUsername(username) != null)
            throw new BaseException(SysEnum.USERNAMEEXIST);
    }

    //检查密码
    private void checkPassword(String password) {
        if (StringUtils.isEmpty(password))
            throw new BaseException(SysEnum.PASSWORDNULL);
    }

    @Override
    public Integer deleteByIds(String[] ids) {
        return sysUserMapper.deleteByIds(ids);
    }

    @Override
    public void updateRoles(String sysUserId, String[] roleIds) {
        //先去掉该用户拥有角色
        Optional.ofNullable(sysUserId).ifPresent(x -> sysUserRoleService.deleteBySysUserId(x));

        //新增用户角色
        Optional.ofNullable(roleIds).ifPresent(x -> {
            List<SysUserRole> list = new ArrayList<>();
            for (String roleId : x) {
                SysUserRole sr = new SysUserRole();
                sr.setId(UuidUtil.get32UUID());
                sr.setSysUserId(sysUserId);
                sr.setRoleId(roleId);
                list.add(sr);
            }
            if (list.size() > 0)
                sysUserRoleService.saveByList(list);
        });
    }
}
