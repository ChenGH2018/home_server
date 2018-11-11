package com.zhwl.home_server.service.shopaudit.impl;

import com.google.common.base.Strings;
import com.zhwl.home_server.bean.Page;
import com.zhwl.home_server.bean.shopaudit.ShopAudit;
import com.zhwl.home_server.mapper.shopaudit.ShopAuditMapper;
import com.zhwl.home_server.service.shopaudit.ShopAuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 说明： 商家审核管理
 * 创建人：ZE
 * 创建时间：2018-11-10
 */
@Service
public class ShopAuditServiceImpl implements ShopAuditService {

    @Autowired
    ShopAuditMapper shopAuditMapper;

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
        return shopAuditMapper.selectBySelective(shopAudit);
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

}

