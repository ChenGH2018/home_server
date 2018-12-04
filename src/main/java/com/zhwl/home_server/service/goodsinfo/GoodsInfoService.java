package com.zhwl.home_server.service.goodsinfo;
import com.zhwl.home_server.bean.goodsinfo.GoodsInfo;
import java.util.List;
import com.zhwl.home_server.bean.Page;
import com.zhwl.home_server.exception.BaseException;

/**
 * 说明： 商品信息管理
 * 创建人：chenguihao
 * 创建时间：2018-12-03
 */
public interface GoodsInfoService {


        /**
         * 新增
         * @param goodsInfo
         * @throws BaseException
         */
        Integer save(GoodsInfo goodsInfo)throws BaseException;

        /**
         * 单个删除
         * @param id
         * @throws BaseException
         */
        Integer deleteOne(String id)throws BaseException;

        /**
         * 修改不为null的字段
         * 主键不能为null
         * @param goodsInfo
         */
        Integer updateBySelective(GoodsInfo goodsInfo)throws BaseException;

        GoodsInfo selectById(String goodsInfoId)throws BaseException;

        /**
         * 查询不为null的字段
         * @param goodsInfo
         * @return
         */
        List<GoodsInfo> selectBySelective(GoodsInfo goodsInfo)throws BaseException;

        /**
         * 列表,分页查询
         * @param pg
         * @throws BaseException
         */
        List<GoodsInfo> getGoodsInfoByPage(Page pg)throws BaseException;

        /**
         * 批量添加
         * @param goodsInfos
         * @throws BaseException
         */
        Integer saveByList(List<GoodsInfo> goodsInfos)throws BaseException;

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

