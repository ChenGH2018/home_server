package com.zhwl.home_server.mapper.shopcomplete;

import com.zhwl.home_server.bean.Page;
import com.zhwl.home_server.bean.shopcomplete.ShopComplete;

import java.util.List;

/**
 * 说明： 商家完善信息
 * 创建时间：2018-11-10
 */
public interface ShopCompleteMapper {

    Integer save(ShopComplete shopComplete);

    Integer deleteOne(String id);

    Integer updateBySelective(ShopComplete shopComplete);

    List<ShopComplete> selectBySelective(ShopComplete shopComplete);

    Integer saveByList(List<ShopComplete> shopCompletes);

    Integer deleteArray(String[] ids);

    List<ShopComplete> getShopCompleteByPage(Page pg);


}
