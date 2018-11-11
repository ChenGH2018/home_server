package com.zhwl.home_server.service.system.impl;

import com.zhwl.home_server.bean.Page;
import com.zhwl.home_server.bean.system.ButtonRole;
import com.zhwl.home_server.bean.system.MenuRole;
import com.zhwl.home_server.bean.system.Role;
import com.zhwl.home_server.exception.BaseException;
import com.zhwl.home_server.mapper.system.role.RoleMapper;
import com.zhwl.home_server.service.system.ButtonRoleService;
import com.zhwl.home_server.service.system.MenuRoleService;
import com.zhwl.home_server.service.system.RoleService;
import com.zhwl.home_server.system.SysEnum;
import com.zhwl.home_server.util.UuidUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service("roleServiceImpl")
@Transactional
public class RoleServiceImpl implements RoleService {

    @Resource(name = "roleMapper")
    private RoleMapper roleMapper;
    @Resource(name = "menuRoleServiceImpl")
    private MenuRoleService menuRoleService;
    @Resource(name = "buttonRoleServiceImpl")
    private ButtonRoleService buttonRoleService;

    @Override
    public List<Role> selectAll() {
        return roleMapper.selectBySelective(null);
    }

    @Override
    public Page selectByPage(Page pg) {
        pg.setData(roleMapper.selectByPage(pg));
        return pg;
    }

    @Override
    public Role selectById(String id) {
        return Optional.ofNullable(id).map(x->{
            Role role = new Role();
            role.setId(x);
            List<Role> list = roleMapper.selectBySelective(role);
            return list != null && list.size() == 1 ? list.get(0) : null;
        }).orElse(null);
    }

    @Override
    public Integer save(Role role) {
        Optional.ofNullable(role.getName()).ifPresent(x->role.setName(x.startsWith("ROLE_") ? x : "ROLE_" + x));
        //验证用户名
        checkName(role.getName());
        role.setId(UuidUtil.get32UUID());
        role.setAddTime(new Date());
        return roleMapper.save(role);
    }

    @Override
    public Integer updateBySelective(Role role) {
        Optional.ofNullable(role.getName()).ifPresent(x->role.setName(x.startsWith("ROLE_") ? x : "ROLE_" + x));
        String name = role.getName(); //角色英文名
        Role r = this.selectById(role.getId());
        Optional.ofNullable(r).ifPresent(x->{
            if (name != null && !x.getName().equals(name)){//当角色英文名改变时
                checkName(name);
            }
        });
        return roleMapper.updateBySelective(role);
    }

    //检查用户角色
    private void checkName(String name) {
        if (StringUtils.isEmpty(name))
            throw new BaseException(SysEnum.ROLENAMENULL);
        if (roleMapper.exitByName(name))
            throw new BaseException(SysEnum.ROLENAMEEXIST);
    }

    @Override
    public Integer deleteByIds(String[] ids) {
        return roleMapper.deleteByIds(ids);
    }

    @Override
    public void updateRoleMenu(String roleId, String[] menuIds) {
        //先去掉该角色拥有菜单
        Optional.ofNullable(roleId).ifPresent(x->menuRoleService.deleteByRoleId(x));

        //新增角色菜单
        Optional.ofNullable(menuIds).ifPresent(x->{
            List<MenuRole> list = new ArrayList<>();
            for (String menuId : x){
                MenuRole mr = new MenuRole();
                mr.setId(UuidUtil.get32UUID());
                mr.setMenuId(menuId);
                mr.setRoleId(roleId);
                list.add(mr);
            }
            if (list.size()>0)
                menuRoleService.saveByList(list);
        });
    }

    @Override
    public void updateRoleButton(String roleId, String[] buttonIds) {
        //先去掉该角色拥有按钮
        Optional.ofNullable(roleId).ifPresent(x->buttonRoleService.deleteByRoleId(x));
        //新增角色按钮
        Optional.ofNullable(buttonIds).ifPresent(x->{
            List<ButtonRole> list = new ArrayList<>();
            for (String buttonId : x){
                ButtonRole br = new ButtonRole();
                br.setId(UuidUtil.get32UUID());
                br.setButtonId(buttonId);
                br.setRoleId(roleId);
                list.add(br);
            }
            if (list.size()>0)
                buttonRoleService.saveByList(list);
        });
    }
}