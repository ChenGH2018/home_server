package com.zhwl.home_server.mapper.serviceinfo;

import com.zhwl.home_server.bean.serviceinfo.ServiceInfo;
import com.zhwl.home_server.bean.Page;
import java.util.List;

/**
 * 说明： 服务信息
 * 创建人：chenguihao
 * 创建时间：2018-11-23
 */
public interface ServiceInfoMapper {

    Integer save(ServiceInfo serviceInfo);

    Integer saveByList(List<ServiceInfo> serviceInfos);

    Integer updateBySelective(ServiceInfo serviceInfo);

    Integer deleteOne(String id);

    List<ServiceInfo> selectBySelective(ServiceInfo serviceInfo);

    List<ServiceInfo> getServiceInfoByPage(Page pg);

    Integer physicsDeleteArray(String[] ids);

    Integer softDeleteArray(String[] ids);
}
