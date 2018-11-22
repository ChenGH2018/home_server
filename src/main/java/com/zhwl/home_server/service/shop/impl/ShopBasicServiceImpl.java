package com.zhwl.home_server.service.shop.impl;

import com.google.common.base.Strings;
import com.zhwl.home_server.bean.Page;
import com.zhwl.home_server.bean.shop.ShopBasic;
import com.zhwl.home_server.bean.system.SysUser;
import com.zhwl.home_server.mapper.shopbasic.ShopBasicMapper;
import com.zhwl.home_server.service.shop.ShopBasicService;
import com.zhwl.home_server.system.UserTypeEnum;
import com.zhwl.home_server.system.WhetherEnum;
import com.zhwl.home_server.util.SysUserUtil;
import com.zhwl.home_server.util.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 说明： 公司基本信息
 * 创建时间：2018-11-09
 */
@Service
@Transactional
public class ShopBasicServiceImpl implements ShopBasicService {

    @Autowired
    ShopBasicMapper shopBasicMapper;

    @Override
    public Integer save(ShopBasic shopBasic) {
        shopBasic.setIsFreeze(WhetherEnum.NO.ordinal()); //冻结
        shopBasic.setIsLogout(WhetherEnum.NO.ordinal()); //注销
        shopBasic.setRegisterTime(new Date());  //注册时间
        return shopBasicMapper.save(shopBasic);
    }

    @Override
    public Integer deleteOne(String shopBasicId) {
        return shopBasicMapper.deleteOne(shopBasicId);
    }

    @Override
    public Integer updateBySelective(ShopBasic shopBasic) {
        if(Strings.isNullOrEmpty(shopBasic.getId()));
        return shopBasicMapper.updateBySelective(shopBasic);
    }

    @Override
    public ShopBasic selectById(String id) {
        ShopBasic shopBasic = new ShopBasic();
        shopBasic.setId(id);
        List<ShopBasic> shopBasics = shopBasicMapper.selectBySelective(shopBasic);
        return shopBasics != null && shopBasics.size() == 1 ? shopBasics.get(0) : null;
    }

    @Override
    public List<ShopBasic> selectBySelective(ShopBasic shopBasic) {
        return shopBasicMapper.selectBySelective(shopBasic);
    }

    @Override
    public List<ShopBasic> getShopBasicByPage(Page pg) {
        SysUser currentUser = SysUserUtil.getCurrentUser();
        //如果不是系统用户只查他自己的数据（sys
        if(currentUser.getUserType() ==null || currentUser.getUserType() != UserTypeEnum.SYSTEMUSER.getType()) {
            ShopBasic shopBasic= (ShopBasic) pg.getPd().get("entity");
            shopBasic.setSysUserId(currentUser.getId());
        }
        return shopBasicMapper.getShopBasicByPage(pg);
    }


    @Override
    public Integer saveByList(List<ShopBasic> shopBasics) {
        shopBasics.forEach(shopBasic ->{
                if(Strings.isNullOrEmpty(shopBasic.getId())){
                    shopBasic.setId(UuidUtil.get32UUID());
                }
        });
        return shopBasicMapper.saveByList(shopBasics);
    }

    @Override
    public Integer deleteArray(String[] ids) {
        return shopBasicMapper.deleteArray(ids);
    }

}

