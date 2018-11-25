package com.zhwl.home_server.mapper.servicecategory;

import com.zhwl.home_server.bean.servicecategory.ServiceCategory;
import com.zhwl.home_server.bean.Page;
import java.util.List;

/**
 * 说明： 服务类别管理
 * 创建人：chenguihao
 * 创建时间：2018-11-23
 */
public interface ServiceCategoryMapper {

    Integer save(ServiceCategory serviceCategory);

    Integer saveByList(List<ServiceCategory> serviceCategorys);

    Integer updateBySelective(ServiceCategory serviceCategory);

    Integer deleteOne(String id);

    List<ServiceCategory> selectBySelective(ServiceCategory serviceCategory);

    List<ServiceCategory> getServiceCategoryByPage(Page pg);

    Integer physicsDeleteArray(String[] ids);

    Integer softDeleteArray(String[] ids);
}
