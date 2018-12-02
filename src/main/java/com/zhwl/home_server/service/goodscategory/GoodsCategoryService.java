package com.zhwl.home_server.service.goodscategory;

import com.zhwl.home_server.bean.Page;
import com.zhwl.home_server.bean.goodscategory.GoodsCategory;
import com.zhwl.home_server.exception.BaseException;

import java.util.List;

/**
 * 说明： 商品类别管理
 * 创建人：chenguihao
 * 创建时间：2018-12-01
 */
public interface GoodsCategoryService {


        /**
         * 新增
         * @param goodsCategory
         * @throws BaseException
         */
        Integer save(GoodsCategory goodsCategory)throws BaseException;

        /**
         * 单个删除
         * @param id
         * @throws BaseException
         */
        Integer deleteOne(String id)throws BaseException;

        /**
         * 修改不为null的字段
         * 主键不能为null
         * @param goodsCategory
         */
        Integer updateBySelective(GoodsCategory goodsCategory)throws BaseException;

        GoodsCategory selectById(String goodsCategoryId)throws BaseException;

        /**
         * 查询不为null的字段
         * @param goodsCategory
         * @return
         */
        List<GoodsCategory> selectBySelective(GoodsCategory goodsCategory)throws BaseException;

        /**
         * 列表,分页查询
         * @param pg
         * @throws BaseException
         */
        List<GoodsCategory> getGoodsCategoryByPage(Page pg)throws BaseException;

        /**
         * 批量添加
         * @param goodsCategory
         * @throws BaseException
         */
        Integer saveByList(List<GoodsCategory> goodsCategorys)throws BaseException;

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

