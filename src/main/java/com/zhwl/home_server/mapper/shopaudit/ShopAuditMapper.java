package com.zhwl.home_server.mapper.shopaudit;

import com.zhwl.home_server.bean.Page;
import com.zhwl.home_server.bean.shopaudit.ShopAudit;

import java.util.HashMap;
import java.util.List;

/**
 * 说明： 商家审核管理
 * 创建人：ZE
 * 创建时间：2018-11-10
 */
public interface ShopAuditMapper {

    Integer save(ShopAudit shopAudit);

    Integer deleteOne(String id);

    Integer updateBySelective(ShopAudit shopAudit);

    List<ShopAudit> selectBySelective(ShopAudit shopAudit);

    Integer saveByList(List<ShopAudit> shopAudits);

    Integer deleteArray(String[] ids);

    List<ShopAudit> getShopAuditByPage(Page pg);


    Integer auditShop(HashMap<String, Object> map);

    Integer updateAuditStatus(ShopAudit shopAudit);
}
