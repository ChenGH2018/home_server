package com.zhwl.home_server.service.shop.impl;

import com.google.common.base.Strings;
import com.zhwl.home_server.bean.Page;
import com.zhwl.home_server.bean.shop.ShopComplete;
import com.zhwl.home_server.bean.shopaudit.ShopAudit;
import com.zhwl.home_server.bean.system.SysUser;
import com.zhwl.home_server.exception.BaseException;
import com.zhwl.home_server.mapper.shopcomplete.ShopCompleteMapper;
import com.zhwl.home_server.service.shop.ShopCompleteService;
import com.zhwl.home_server.service.shopaudit.ShopAuditService;
import com.zhwl.home_server.util.UuidUtil;
import com.zhwl.home_server.websocket.WebSocketUtil;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 说明： 商家完善信息
 * 创建时间：2018-11-10
 */
@Service
@Transactional
public class ShopCompleteServiceImpl implements ShopCompleteService {

    @Autowired
    ShopCompleteMapper shopCompleteMapper;
    @Autowired
    ShopAuditService shopAuditService;
    @Autowired
    WebSocketUtil webScoketUtil;

    @Override
    public Integer save(ShopComplete shopComplete) {
        shopCompleteNoNull(shopComplete);
        ShopAudit shopAudit = shopComplete.getShopAudit();
        shopAudit.setId(UuidUtil.get32UUID());
        shopAuditNoNull(shopAudit);
        shopAudit.setApplicationTime(new Date());
        shopAudit.setAuditStatus(0); //未审核
        shopAudit.setShopCompleteId(shopComplete.getId());
        Integer completeSave = shopCompleteMapper.save(shopComplete);
        Integer shopAuditSave = shopAuditService.save(shopAudit);
        if (completeSave != 1 || shopAuditSave != 1) throw new BaseException("提交失败");
        webScoketUtil.sendShopAuditMessage(1);
        return 1;
    }

    private void shopAuditNoNull(@NonNull ShopAudit shopAudit) {
        if (Strings.isNullOrEmpty(shopAudit.getApplicationPerson())) throw new BaseException("申请人不能为空");
    }

    private void shopCompleteNoNull(@NonNull ShopComplete shopComplete) {
        if (Strings.isNullOrEmpty(shopComplete.getShopBasicId())) throw new BaseException("商家基本ID不能为空");
        if (Strings.isNullOrEmpty(shopComplete.getShopName())) throw new BaseException("店铺名不能为空");
        if (Strings.isNullOrEmpty(shopComplete.getBusinessLicenseUrl())) throw new BaseException("营业执照不能为空");
        if (Strings.isNullOrEmpty(shopComplete.getBusinessRegistrationNumber())) throw new BaseException("工商注册号");
        if (Strings.isNullOrEmpty(shopComplete.getLegalRepresentative())) throw new BaseException("法人代表不能为空");
        if (Strings.isNullOrEmpty(shopComplete.getRegisteredAddress())) throw new BaseException("注册地址不能为空");
        if (Strings.isNullOrEmpty(shopComplete.getUnifiedSocialCreditCode())) throw new BaseException("统一社会信用代码");
        if (Strings.isNullOrEmpty(shopComplete.getCompanyType())) throw new BaseException("公司类型不能为空");
        if (null == shopComplete.getRegisteredCapital()) throw new BaseException("注册资本不能为空");
        if (null == shopComplete.getEstablishmentDate()) throw new BaseException("成立日期不能为空");
        if (null == shopComplete.getBusinessPeriodBegins()) throw new BaseException("营业期限开始日期不能为空");
        if (null == shopComplete.getBusinessPeriodEnd()) throw new BaseException("营业期限结束日期不能为空");
    }

    @Override
    public Integer deleteOne(String shopCompleteId) {
        return shopCompleteMapper.deleteOne(shopCompleteId);
    }

    @Override
    public Integer updateBySelective(ShopComplete shopComplete) {
        if (Strings.isNullOrEmpty(shopComplete.getId())) throw new RuntimeException("修改ID不能为空");
        Integer updateCount = shopCompleteMapper.updateBySelective(shopComplete);
        //!=1 return
        if (updateCount != 1) return 0;
        //修改了完善信息则修改审核状态为未审核
        ShopAudit shopAudit = new ShopAudit();
        shopAudit.setAuditStatus(0);
        shopAudit.setAuditResult(2);
        shopAudit.setShopCompleteId(shopComplete.getId());
        if (shopAuditService.updateAuditStatus(shopAudit) != 1) throw new BaseException("异常操作");
        return 1;
    }

    @Override
    public ShopComplete selectById(String id) {
        ShopComplete shopComplete = new ShopComplete();
        shopComplete.setId(id);
        List<ShopComplete> shopCompletes = shopCompleteMapper.selectBySelective(shopComplete);
        return shopCompletes != null && shopCompletes.size() == 1 ? shopCompletes.get(0) : null;
    }

    @Override
    public List<ShopComplete> selectBySelective(ShopComplete shopComplete) {
        shopComplete.setQueryAudit(true);//查询审核情况
        return shopCompleteMapper.selectBySelective(shopComplete);
    }

    @Override
    public List<ShopComplete> getShopCompleteByPage(Page pg) {
        return shopCompleteMapper.getShopCompleteByPage(pg);
    }

    @Override
    public Integer saveByList(List<ShopComplete> shopCompletes) {
        return shopCompleteMapper.saveByList(shopCompletes);
    }

    @Override
    public Integer deleteArray(String[] ids) {
        return shopCompleteMapper.deleteArray(ids);
    }

    @Override
    public ShopComplete getShopCompleteAndAudit(String shopBasicId) {
        if (Strings.isNullOrEmpty(shopBasicId)) throw new BaseException("商家基本ID不能为空");
        ShopComplete shopComplete = new ShopComplete();
        shopComplete.setShopBasicId(shopBasicId);
        shopComplete.setQueryAudit(true);
        List<ShopComplete> shopCompletes = selectBySelective(shopComplete);
        if (shopCompletes.isEmpty()) return null;
        if (shopCompletes.size() > 1) throw new BaseException("数据异常");
        return shopCompletes.get(0);
    }

    @Override
    public SysUser selectUserByCompleteId(String shopCompleteId) {
        return shopCompleteMapper.selectUserByCompleteId(shopCompleteId);
    }

}

