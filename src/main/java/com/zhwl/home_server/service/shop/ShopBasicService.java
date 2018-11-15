package com.zhwl.home_server.service.shop;

import com.zhwl.home_server.bean.Page;
import com.zhwl.home_server.bean.shopbasic.ShopBasic;

import java.util.List;

/**
 * 说明： 公司基本信息接口
 * 创建时间：2018-11-09
 */
public interface ShopBasicService {

    /**
     * 新增
     *
     * @param shopBasic
     * @throws Exception
     */
    Integer save(ShopBasic shopBasic);

    /**
     * 单个删除
     *
     * @param id
     * @throws Exception
     */
    Integer deleteOne(String id);

    /**
     * 修改不为null的字段
     * 主键不能为null
     *
     * @param shopBasic
     */
    Integer updateBySelective(ShopBasic shopBasic);

    ShopBasic selectById(String shopBasicId);

    /**
     * 查询不为null的字段
     *
     * @param shopBasic
     * @return
     */
    List<ShopBasic> selectBySelective(ShopBasic shopBasic);

    /**
     * 列表,分页查询
     *
     * @param pg
     * @throws Exception
     */
    List<ShopBasic> getShopBasicByPage(Page pg);

    /**
     * 批量添加
     *
     * @param shopBasic
     * @throws Exception
     */
    Integer saveByList(List<ShopBasic> shopBasic);

    /**
     * 批量删除
     *
     * @param ids
     * @throws Exception
     */
    Integer deleteArray(String[] ids);

}

