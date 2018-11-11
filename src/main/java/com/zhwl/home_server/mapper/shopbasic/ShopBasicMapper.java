package com.zhwl.home_server.mapper.shopbasic;

import com.zhwl.home_server.bean.Page;
import com.zhwl.home_server.bean.shopbasic.ShopBasic;

import java.util.List;

/**
 * 说明： 公司基本信息
 * 创建时间：2018-11-09
 */
public interface ShopBasicMapper {

    Integer save(ShopBasic shopBasic);

    Integer deleteOne(String id);

    Integer updateBySelective(ShopBasic shopBasic);

    List<ShopBasic> selectBySelective(ShopBasic shopBasic);

    Integer saveByList(List<ShopBasic> shopBasics);

    Integer deleteArray(String[] ids);

    List<ShopBasic> getShopBasicByPage(Page pg);


}
