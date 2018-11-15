package com.zhwl.home_server.service.shop.impl;

import com.google.common.base.Strings;
import com.zhwl.home_server.bean.Page;
import com.zhwl.home_server.bean.shopcomplete.ShopComplete;
import com.zhwl.home_server.exception.BaseException;
import com.zhwl.home_server.mapper.shopcomplete.ShopCompleteMapper;
import com.zhwl.home_server.service.shop.ShopCompleteService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 说明： 商家完善信息
 * 创建时间：2018-11-10
 */
@Service
public class ShopCompleteServiceImpl implements ShopCompleteService {

    @Autowired
    ShopCompleteMapper shopCompleteMapper;

    @Override
    public Integer save(ShopComplete shopComplete) {
        shopCompleteNoNull(shopComplete);
        return shopCompleteMapper.save(shopComplete);
    }

    private void shopCompleteNoNull(@NonNull ShopComplete shopComplete) {
        if(Strings.isNullOrEmpty(shopComplete.getShopBasicId())) throw new BaseException("商家基本ID不能为空");
        if(Strings.isNullOrEmpty(shopComplete.getShopName())) throw new BaseException("店铺名不能为空");
        if(Strings.isNullOrEmpty(shopComplete.getBusinessLicenseUrl())) throw new BaseException("营业执照不能为空");
        if(Strings.isNullOrEmpty(shopComplete.getBusinessRegistrationNumber())) throw new BaseException("工商注册号");
        if(Strings.isNullOrEmpty(shopComplete.getLegalRepresentative())) throw new BaseException("法人代表不能为空");
        if(Strings.isNullOrEmpty(shopComplete.getRegisteredAddress())) throw new BaseException("注册地址不能为空");
        if(Strings.isNullOrEmpty(shopComplete.getUnifiedSocialCreditCode())) throw new BaseException("统一社会信用代码");
        if(Strings.isNullOrEmpty(shopComplete.getCompanyType())) throw new BaseException("公司类型不能为空");
        if(null == shopComplete.getRegisteredCapital()) throw new BaseException("注册资本不能为空");
        if(null == shopComplete.getEstablishmentDate()) throw new BaseException("成立日期不能为空");
        if(null == shopComplete.getBusinessPeriodBegins()) throw new BaseException("营业期限开始日期不能为空");
        if(null == shopComplete.getBusinessPeriodEnd()) throw new BaseException("营业期限结束日期不能为空");
    }

    @Override
    public Integer deleteOne(String shopCompleteId) {
        return shopCompleteMapper.deleteOne(shopCompleteId);
    }

    @Override
    public Integer updateBySelective(ShopComplete shopComplete) {
        if(Strings.isNullOrEmpty(shopComplete.getId())) throw new RuntimeException("修改ID不能为空");
        return shopCompleteMapper.updateBySelective(shopComplete);
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

}

