package com.zhwl.home_server.service.categoryspecification;

import com.zhwl.home_server.bean.Page;
import com.zhwl.home_server.bean.categoryspecification.CategorySpecification;
import com.zhwl.home_server.exception.BaseException;

import java.util.List;

/**
 * 说明： 类别规格管理
 * 创建人：chenguihao
 * 创建时间：2018-12-01
 */
public interface CategorySpecificationService {


        /**
         * 新增
         * @param categorySpecification
         * @throws BaseException
         */
        Integer save(CategorySpecification categorySpecification)throws BaseException;

        /**
         * 单个删除
         * @param id
         * @throws BaseException
         */
        Integer deleteOne(String id)throws BaseException;

        /**
         * 修改不为null的字段
         * 主键不能为null
         * @param categorySpecification
         */
        Integer updateBySelective(CategorySpecification categorySpecification)throws BaseException;

        CategorySpecification selectById(String categorySpecificationId)throws BaseException;

        /**
         * 查询不为null的字段
         * @param categorySpecification
         * @return
         */
        List<CategorySpecification> selectBySelective(CategorySpecification categorySpecification)throws BaseException;

        /**
         * 列表,分页查询
         * @param pg
         * @throws BaseException
         */
        List<CategorySpecification> getCategorySpecificationByPage(Page pg)throws BaseException;

        /**
         * 批量添加
         * @param categorySpecification
         * @throws BaseException
         */
        Integer saveByList(List<CategorySpecification> categorySpecifications)throws BaseException;

        /**
         * 批量物理删除
         * @param ids
         * @throws BaseException
         */
        Integer physicsDeleteArray(String[] ids) throws BaseException;
}

