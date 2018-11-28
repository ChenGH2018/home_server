package com.zhwl.home_server.service.unitmanagement;

import com.zhwl.home_server.bean.Page;
import com.zhwl.home_server.bean.unitmanagement.UnitManagement;
import com.zhwl.home_server.exception.BaseException;

import java.util.List;

/**
 * 说明： 价钱单位管理
 * 创建人：chenguihao
 * 创建时间：2018-11-27
 */
public interface UnitManagementService {


        /**
         * 新增
         * @param unitManagement
         * @throws BaseException
         */
        Integer save(UnitManagement unitManagement)throws BaseException;

        /**
         * 单个删除
         * @param id
         * @throws BaseException
         */
        Integer deleteOne(String id)throws BaseException;

        /**
         * 修改不为null的字段
         * 主键不能为null
         * @param unitManagement
         */
        Integer updateBySelective(UnitManagement unitManagement)throws BaseException;

        UnitManagement selectById(String unitManagementId)throws BaseException;

        /**
         * 查询不为null的字段
         * @param unitManagement
         * @return
         */
        List<UnitManagement> selectBySelective(UnitManagement unitManagement)throws BaseException;

        /**
         * 列表,分页查询
         * @param pg
         * @throws BaseException
         */
        List<UnitManagement> getUnitManagementByPage(Page pg)throws BaseException;

        /**
         * 批量添加
         * @param unitManagement
         * @throws BaseException
         */
        Integer saveByList(List<UnitManagement> unitManagements)throws BaseException;

        /**
         * 批量物理删除
         * @param ids
         * @throws BaseException
         */
        Integer physicsDeleteArray(String[] ids) throws BaseException;
}

