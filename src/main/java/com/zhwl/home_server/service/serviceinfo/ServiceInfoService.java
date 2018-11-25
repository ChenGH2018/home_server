package com.zhwl.home_server.service.serviceinfo;

import com.zhwl.home_server.bean.Page;
import com.zhwl.home_server.bean.serviceinfo.ServiceInfo;
import com.zhwl.home_server.exception.BaseException;

import java.util.List;

/**
 * 说明： 服务信息
 * 创建人：chenguihao
 * 创建时间：2018-11-23
 */
public interface ServiceInfoService {


        /**
         * 新增
         * @param serviceInfo
         * @throws BaseException
         */
        Integer save(ServiceInfo serviceInfo)throws BaseException;

        /**
         * 单个删除
         * @param id
         * @throws BaseException
         */
        Integer deleteOne(String id)throws BaseException;

        /**
         * 修改不为null的字段
         * 主键不能为null
         * @param serviceInfo
         */
        Integer updateBySelective(ServiceInfo serviceInfo)throws BaseException;

        ServiceInfo selectById(String serviceInfoId)throws BaseException;

        /**
         * 查询不为null的字段
         * @param serviceInfo
         * @return
         */
        List<ServiceInfo> selectBySelective(ServiceInfo serviceInfo)throws BaseException;

        /**
         * 列表,分页查询
         * @param pg
         * @throws BaseException
         */
        List<ServiceInfo> getServiceInfoByPage(Page pg)throws BaseException;

        /**
         * 批量添加
         * @param serviceInfo
         * @throws BaseException
         */
        Integer saveByList(List<ServiceInfo> serviceInfos)throws BaseException;

        /**
         * 批量物理删除
         * @param ids
         * @throws BaseException
         */
        Integer physicsDeleteArray(String[] ids) throws BaseException;
        /**
        * 批量软删除
        * @param ids
        * @throws BaseException
        */
        Integer softDeleteArray(String[] ids) throws BaseException;
}

