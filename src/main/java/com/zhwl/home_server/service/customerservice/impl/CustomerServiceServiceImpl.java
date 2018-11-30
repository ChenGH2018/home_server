package com.zhwl.home_server.service.customerservice.impl;

import com.google.common.base.Strings;
import com.zhwl.home_server.bean.Page;
import com.zhwl.home_server.bean.customerservice.CustomerService;
import com.zhwl.home_server.bean.shop.ShopBasic;
import com.zhwl.home_server.bean.system.SysUser;
import com.zhwl.home_server.bean.system.SysUserRole;
import com.zhwl.home_server.exception.BaseException;
import com.zhwl.home_server.mapper.customerservice.CustomerServiceMapper;
import com.zhwl.home_server.service.customerservice.CustomerServiceService;
import com.zhwl.home_server.service.system.SysUserRoleService;
import com.zhwl.home_server.service.system.SysUserService;
import com.zhwl.home_server.system.UserTypeEnum;
import com.zhwl.home_server.util.SysUserUtil;
import com.zhwl.home_server.util.Tools;
import com.zhwl.home_server.util.UuidUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 说明： 客服管理
 * 创建人：chenguihao
 * 创建时间：2018-11-26
 */

@Service
@Transactional
@Slf4j
public class CustomerServiceServiceImpl implements CustomerServiceService {

    @Autowired
    CustomerServiceMapper customerServiceMapper;
    @Autowired
    SysUserService sysUserService;
    @Autowired
    SysUserRoleService sysUserRoleService;

    @Override
    public Integer save(CustomerService customerService) throws BaseException {
        customerService.setIsFreeze(0);
        customerService.setIsDelete(0);
        return customerServiceMapper.save(customerService);
    }

    @Override
    public Integer deleteOne(String customerServiceId) throws BaseException {
        return customerServiceMapper.deleteOne(customerServiceId);
    }

    @Override
    public Integer updateBySelective(CustomerService customerService) throws BaseException {
        if (Strings.isNullOrEmpty(customerService.getId())) throw new RuntimeException("要修改的ID不能为空");
        if (!Strings.isNullOrEmpty(customerService.getPassword())) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            customerService.setPassword(encoder.encode(customerService.getPassword()));
        }
        return customerServiceMapper.updateBySelective(customerService);
    }


    @Override
    public CustomerService selectById(String id) throws BaseException {
        CustomerService customerService = new CustomerService();
        customerService.setId(id);
        List<CustomerService> customerServices = customerServiceMapper.selectBySelective(customerService);
        return customerServices != null && customerServices.size() == 1 ? customerServices.get(0) : null;
    }

    @Override
    public List<CustomerService> selectBySelective(CustomerService customerService) throws BaseException {
        ShopBasic currentShopBasic = SysUserUtil.getCurrentShopBasic();
        if (null == currentShopBasic)
            return null;
        customerService.setShopBasicId(currentShopBasic.getId());
        return customerServiceMapper.selectBySelective(customerService);

    }

    @Override
    public List<CustomerService> getCustomerServiceByPage(Page pg) throws BaseException {
        ShopBasic currentShopBasic = SysUserUtil.getCurrentShopBasic();
        if (null == currentShopBasic)
            return null;
        CustomerService queryEntity = (CustomerService) pg.getPd().get("entity");
        queryEntity.setShopBasicId(currentShopBasic.getId());
        return customerServiceMapper.getCustomerServiceByPage(pg);
    }

    @Override
    public Integer saveByList(List<CustomerService> customerServices) throws BaseException {
        return customerServiceMapper.saveByList(customerServices);
    }

    /**
     * 批量物理删除
     *
     * @param ids
     * @throws BaseException
     */
    @Override
    public Integer physicsDeleteArray(String[] ids) throws BaseException {
        return customerServiceMapper.physicsDeleteArray(ids);
    }

    @Override
    public boolean saveCustomerService(CustomerService customerService) {
        ShopBasic currentShopBasic = SysUserUtil.getCurrentShopBasic();
        if (null == currentShopBasic)
            throw new BaseException("拒绝访问：非商家用户");
        validateCustomerService(customerService);
        SysUser sysUser = new SysUser();
        sysUser.setId(UuidUtil.get32UUID());
        sysUser.setUserType(UserTypeEnum.CS.getType());
        sysUser.setUsername(customerService.getUsername());
        sysUser.setPassword(customerService.getPassword());
        sysUser.setName(customerService.getFullName());
        Integer save = sysUserService.save(sysUser);
        if (save != 1) throw new BaseException("新增用户失败");
        //分配角色
        SysUserRole sysUserRole = new SysUserRole();
        sysUserRole.setId(UuidUtil.get32UUID());
        sysUserRole.setSysUserId(sysUser.getId());
        sysUserRole.setRoleId("199288efc0e145f69660cd940b0e23f2");//客服角色ID
        Integer roleSave = sysUserRoleService.save(sysUserRole);
        if (1 != roleSave) throw new BaseException("角色分配失败");
        //保存客服信息
        customerService.setId(UuidUtil.get32UUID());
        customerService.setSysUserId(sysUser.getId());
        customerService.setShopBasicId(currentShopBasic.getId());
        Integer csSave = save(customerService);
        if (1 != csSave) throw new BaseException("客服信息保存失败");
        return true;
    }

    @Override
    public Integer softDeleteArray(String[] ids) throws BaseException {
        return customerServiceMapper.softDeleteArray(ids);
    }

    public void validateCustomerService(CustomerService customerService) {
        if (null == customerService)
            throw new BaseException("客服信息不能为空");
        if (Strings.isNullOrEmpty(customerService.getUsername()) || Strings.isNullOrEmpty(customerService.getPassword()) || Strings.isNullOrEmpty(customerService.getFullName()) ||
                Strings.isNullOrEmpty(customerService.getPhone()))
            throw new BaseException("用户名，密码，客服名字，手机号不能为空");
        customerService.setUsername(customerService.getUsername().trim());
        if (Strings.isNullOrEmpty(customerService.getUsername()) || customerService.getUsername().trim().length() < 6 || customerService.getUsername().trim().length() > 20) {
            throw new BaseException("用户名不能为空、长度在6-20位之间");
        }
        if (!Tools.isPhone(customerService.getPhone()))
            throw new BaseException("手机号格式不正确");
    }
}

