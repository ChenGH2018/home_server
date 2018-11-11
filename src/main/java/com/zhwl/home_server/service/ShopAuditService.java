package com.zhwl.home_server.service;

import com.zhwl.home_server.bean.Page;
import com.zhwl.home_server.bean.shopaudit.ShopAudit;

import java.util.List;

/**
 * 说明： 商家审核管理
 * 创建人：chenguihao
 * 创建时间：2018-11-11
 */
public interface ShopAuditService {


    /**
     * 新增
     *
     * @param shopAudit
     * @throws Exception
     */
    Integer save(ShopAudit shopAudit) throws Exception;

    /**
     * 单个删除
     *
     * @param id
     * @throws Exception
     */
    Integer deleteOne(String id) throws Exception;

    /**
     * 修改不为null的字段
     * 主键不能为null
     *
     * @param shopAudit
     */
    Integer updateBySelective(ShopAudit shopAudit) throws Exception;

    ShopAudit selectById(String shopAuditId) throws Exception;

    /**
     * 查询不为null的字段
     *
     * @param shopAudit
     * @return
     */
    List<ShopAudit> selectBySelective(ShopAudit shopAudit) throws Exception;

    /**
     * 列表,分页查询
     *
     * @param pg
     * @throws Exception
     */
    List<ShopAudit> getShopAuditByPage(Page pg) throws Exception;

    /**
     * 批量添加
     *
     * @param shopAudit
     * @throws Exception
     */
    Integer saveByList(List<ShopAudit> shopAudit) throws Exception;

    /**
     * 批量删除
     *
     * @param ids
     * @throws Exception
     */
    Integer deleteArray(String[] ids) throws Exception;


}

