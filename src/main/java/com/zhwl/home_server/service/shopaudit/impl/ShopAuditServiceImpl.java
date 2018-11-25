package com.zhwl.home_server.service.shopaudit.impl;

import com.google.common.base.Strings;
import com.zhwl.home_server.bean.Page;
import com.zhwl.home_server.bean.shopaudit.ShopAudit;
import com.zhwl.home_server.bean.system.Role;
import com.zhwl.home_server.exception.BaseException;
import com.zhwl.home_server.mapper.shopaudit.ShopAuditMapper;
import com.zhwl.home_server.service.shop.ShopCompleteService;
import com.zhwl.home_server.service.shopaudit.ShopAuditService;
import com.zhwl.home_server.service.system.RoleService;
import com.zhwl.home_server.service.system.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

/**
 * 说明： 商家审核管理
 * 创建人：ZE
 * 创建时间：2018-11-10
 */
@Service
@Transactional
public class ShopAuditServiceImpl implements ShopAuditService {

    @Autowired
    ShopAuditMapper shopAuditMapper;
    @Autowired
    RoleService roleService;
    @Autowired
    ShopCompleteService shopCompleteService;
    @Autowired
    SysUserRoleService sysUserRoleService;

    @Override
    public Integer save(ShopAudit shopAudit) {
        if (Strings.isNullOrEmpty(shopAudit.getShopCompleteId())) throw new RuntimeException("商家ID不能为空");
        return shopAuditMapper.save(shopAudit);
    }

    @Override
    public Integer deleteOne(String shopAuditId) {
        return shopAuditMapper.deleteOne(shopAuditId);
    }

    @Override
    public Integer updateBySelective(ShopAudit shopAudit) {
        if (Strings.isNullOrEmpty(shopAudit.getId())) throw new RuntimeException("ID不能为空");
        return shopAuditMapper.updateBySelective(shopAudit);
    }

    @Override
    public ShopAudit selectById(String id) {
        ShopAudit shopAudit = new ShopAudit();
        shopAudit.setId(id);
        List<ShopAudit> shopAudits = shopAuditMapper.selectBySelective(shopAudit);
        return shopAudits != null && shopAudits.size() == 1 ? shopAudits.get(0) : null;
    }

    @Override
    public List<ShopAudit> selectBySelective(ShopAudit shopAudit) {
        List<ShopAudit> shopAudits = shopAuditMapper.selectBySelective(shopAudit);
        return shopAudits;
    }

    @Override
    public List<ShopAudit> getShopAuditByPage(Page pg) {
        return shopAuditMapper.getShopAuditByPage(pg);
    }


    @Override
    public Integer saveByList(List<ShopAudit> shopAudits) {
        return shopAuditMapper.saveByList(shopAudits);
    }

    @Override
    public Integer deleteArray(String[] ids) {
        return shopAuditMapper.deleteArray(ids);
    }

    @Override
    public Integer auditShop(ShopAudit shopAudit) {
        if (Strings.isNullOrEmpty(shopAudit.getShopCompleteId()))
            throw new BaseException("商家ID不能为空");
        if (shopAudit.getAuditStatus() != 1)
            throw new BaseException("审核结果必须是已审核");
        Integer auditCount = updateAuditStatus(shopAudit);
        if (auditCount != 1)
            throw new BaseException("审核失败");
        updateRole(shopAudit);
        return 1;
    }

    @Override
    public Integer updateAuditStatus(ShopAudit shopAudit) {
        return shopAuditMapper.updateAuditStatus(shopAudit);
    }

    //审核后初始化操作：修改该商家的角色为已审核商家
    private Integer updateRole(ShopAudit shopAudit) {
        if (shopAudit.getAuditResult() == 1) {//审核通过才初始化
            Role role = new Role();
            role.setName("ROLE_activate_shop");
            List<Role> roles = roleService.selectBySelective(role);
            if (roles.size() != 1) throw new BaseException("角色数量异常");
            HashMap<String, Object> map = new HashMap<>();
            map.put("shopId", shopAudit.getId());
            map.put("roleId", roles.get(0).getId());
            return sysUserRoleService.updateRole(map);
        }
        return 0;
    }

}

