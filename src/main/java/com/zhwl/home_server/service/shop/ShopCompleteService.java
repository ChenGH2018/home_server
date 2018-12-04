package com.zhwl.home_server.service.shop;

import com.zhwl.home_server.bean.Page;
import com.zhwl.home_server.bean.shop.ShopComplete;
import com.zhwl.home_server.bean.system.SysUser;

import java.util.List;
/** 
 * 说明： 商家完善信息接口
 * 创建时间：2018-11-10
 * @version
 */
public interface ShopCompleteService{

    /**新增
    * @param shopComplete
    * @throws Exception
    */
    Integer save(ShopComplete shopComplete);

    /**单个删除
        * @param  id
        * @throws Exception
        */
    Integer deleteOne(String id);

    /**
        * 修改不为null的字段
        *      主键不能为null
        * @param shopComplete
        */
    Integer updateBySelective(ShopComplete shopComplete);

ShopComplete selectById(String shopCompleteId);

    /**
    * 查询不为null的字段
    * @param shopComplete
    * @return
    */
    List<ShopComplete> selectBySelective(ShopComplete shopComplete);

    /**
    * 列表,分页查询
    * @param pg
    * @throws Exception
    */
    List<ShopComplete> getShopCompleteByPage(Page pg);

    /**批量添加
        * @param shopComplete
        * @throws Exception
        */
    Integer saveByList(List<ShopComplete> shopComplete);

    /**批量删除
        * @param ids
        * @throws Exception
        */
    Integer deleteArray(String[] ids);


    ShopComplete getShopCompleteAndAudit(String shopBasicId);

    SysUser selectUserByCompleteId(String shopCompleteId);
}

