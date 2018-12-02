package com.zhwl.home_server.service.goodscategory.impl;

import com.google.common.base.Strings;
import com.zhwl.home_server.bean.Page;
import com.zhwl.home_server.bean.goodscategory.GoodsCategory;
import com.zhwl.home_server.exception.BaseException;
import com.zhwl.home_server.mapper.goodscategory.GoodsCategoryMapper;
import com.zhwl.home_server.service.goodscategory.GoodsCategoryService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 说明： 商品类别管理
 * 创建人：chenguihao
 * 创建时间：2018-12-01
 */

@Service
@Transactional
@Log4j
public class GoodsCategoryServiceImpl implements GoodsCategoryService {

    @Autowired
    GoodsCategoryMapper goodsCategoryMapper;

    @Override
    public Integer save(GoodsCategory goodsCategory) throws BaseException {
        return goodsCategoryMapper.save(goodsCategory);
    }

    @Override
    public Integer deleteOne(String goodsCategoryId) throws BaseException {
        return goodsCategoryMapper.deleteOne(goodsCategoryId);
    }

    @Override
    public Integer updateBySelective(GoodsCategory goodsCategory) throws BaseException {
        if(Strings.isNullOrEmpty(goodsCategory.getId())) throw new RuntimeException("要修改的ID不能为空");
        return goodsCategoryMapper.updateBySelective(goodsCategory);
    }


    @Override
    public GoodsCategory selectById(String id) throws BaseException{
        GoodsCategory goodsCategory = new GoodsCategory();
        goodsCategory.setId(id);
        List<GoodsCategory> goodsCategorys = goodsCategoryMapper.selectBySelective(goodsCategory);
        return goodsCategorys != null && goodsCategorys.size() == 1 ? goodsCategorys.get(0) : null;
    }

    @Override
    public List<GoodsCategory> selectBySelective(GoodsCategory goodsCategory) throws BaseException{
        return goodsCategoryMapper.selectBySelective(goodsCategory);

    }

    @Override
    public List<GoodsCategory> getGoodsCategoryByPage(Page pg) throws BaseException{
        return goodsCategoryMapper.getGoodsCategoryByPage(pg);
    }

    @Override
    public Integer saveByList(List<GoodsCategory> goodsCategorys) throws BaseException{
        return goodsCategoryMapper.saveByList(goodsCategorys);
    }

    /**
    * 批量物理删除
    * @param ids
    * @throws BaseException
    */
    @Override
    public Integer physicsDeleteArray(String[] ids) throws BaseException{
        return goodsCategoryMapper.physicsDeleteArray(ids);
    }
    /**
    * 批量软删除
    * @param ids
    * @throws BaseException
    */
    public Integer softDeleteArray(String[] ids) throws BaseException{
        return goodsCategoryMapper.softDeleteArray(ids);
    }
}

