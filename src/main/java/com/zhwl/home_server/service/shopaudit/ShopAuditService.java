package com.zhwl.home_server.service.shopaudit;

import com.zhwl.home_server.bean.Page;
import com.zhwl.home_server.bean.shopaudit.ShopAudit;

import java.util.List;

/**
 * 说明： 商家审核管理接口
 * 创建时间：2018-11-10
 */
public interface ShopAuditService {

    /**
     * 新增
     *
     * @param shopAudit
     * @throws Exception
     */
    Integer save(ShopAudit shopAudit);

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
     * @param shopAudit
     */
    Integer updateBySelective(ShopAudit shopAudit);

    ShopAudit selectById(String shopAuditId);

    /**
     * 查询不为null的字段
     *
     * @param shopAudit
     * @return
     */
    List<ShopAudit> selectBySelective(ShopAudit shopAudit);

    /**
     * 列表,分页查询
     *
     * @param pg
     * @throws Exception
     */
    List<ShopAudit> getShopAuditByPage(Page pg);

    /**
     * 批量添加
     *
     * @param shopAudit
     * @throws Exception
     */
    Integer saveByList(List<ShopAudit> shopAudit);

    /**
     * 批量删除
     *
     * @param ids
     * @throws Exception
     */
    Integer deleteArray(String[] ids);


    Integer auditShop(ShopAudit shopAudit);

    Integer updateAuditStatus(ShopAudit shopAudit);
}

