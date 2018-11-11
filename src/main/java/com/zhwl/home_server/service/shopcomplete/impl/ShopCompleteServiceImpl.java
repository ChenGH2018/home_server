package com.zhwl.home_server.service.shopcomplete.impl;

import com.google.common.base.Strings;
import com.zhwl.home_server.bean.Page;
import com.zhwl.home_server.bean.shopcomplete.ShopComplete;
import com.zhwl.home_server.mapper.shopcomplete.ShopCompleteMapper;
import com.zhwl.home_server.service.shopcomplete.ShopCompleteService;
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
        return shopCompleteMapper.save(shopComplete);
    }

    @Override
    public Integer deleteOne(String shopCompleteId) {
        return shopCompleteMapper.deleteOne(shopCompleteId);
    }

    @Override
    public Integer updateBySelective(ShopComplete shopComplete) {
        if(Strings.isNullOrEmpty(shopComplete.getId())) throw new RuntimeException("ID不能为空");
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

