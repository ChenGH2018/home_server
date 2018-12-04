package com.zhwl.home_server.service.categoryspecification.impl;

import com.google.common.base.Strings;
import com.zhwl.home_server.bean.Page;
import com.zhwl.home_server.bean.categoryspecification.CategorySpecification;
import com.zhwl.home_server.exception.BaseException;
import com.zhwl.home_server.mapper.categoryspecification.CategorySpecificationMapper;
import com.zhwl.home_server.service.categoryspecification.CategorySpecificationService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 说明： 类别规格管理
 * 创建人：chenguihao
 * 创建时间：2018-12-01
 */

@Service
@Transactional
@Log4j
public class CategorySpecificationServiceImpl implements CategorySpecificationService {

    @Autowired
    CategorySpecificationMapper categorySpecificationMapper;

    @Override
    public Integer save(CategorySpecification categorySpecification) throws BaseException {
        validateCategorySpecification(categorySpecification);
        return categorySpecificationMapper.save(categorySpecification);
    }

    private void validateCategorySpecification(CategorySpecification categorySpecification) {
        if(Strings.isNullOrEmpty(categorySpecification.getCategoryName()))
            throw new BaseException("类别规格名字不能为空");
        if(null == categorySpecification.getCategoryValues() || categorySpecification.getCategoryValues().length == 0)
            throw new BaseException("类别规格值不能为空");
        if(Strings.isNullOrEmpty(categorySpecification.getGoodsCategoryId()))
            throw new BaseException("所属商品规格ID不能为空");
        if(null == categorySpecification.getIsCustomize())
            throw new BaseException("是否可自定义不能为空");
        if(null == categorySpecification.getIsRequire())
            throw new BaseException("是否必填不能为空");


    }

    @Override
    public Integer deleteOne(String categorySpecificationId) throws BaseException {
        return categorySpecificationMapper.deleteOne(categorySpecificationId);
    }

    @Override
    public Integer updateBySelective(CategorySpecification categorySpecification) throws BaseException {
        if(Strings.isNullOrEmpty(categorySpecification.getId())) throw new RuntimeException("要修改的ID不能为空");
        return categorySpecificationMapper.updateBySelective(categorySpecification);
    }


    @Override
    public CategorySpecification selectById(String id) throws BaseException{
        CategorySpecification categorySpecification = new CategorySpecification();
        categorySpecification.setId(id);
        List<CategorySpecification> categorySpecifications = categorySpecificationMapper.selectBySelective(categorySpecification);
        return categorySpecifications != null && categorySpecifications.size() == 1 ? categorySpecifications.get(0) : null;
    }

    @Override
    public List<CategorySpecification> selectBySelective(CategorySpecification categorySpecification) throws BaseException{
        return categorySpecificationMapper.selectBySelective(categorySpecification);

    }

    @Override
    public List<CategorySpecification> getCategorySpecificationByPage(Page pg) throws BaseException{
        return categorySpecificationMapper.getCategorySpecificationByPage(pg);
    }

    @Override
    public Integer saveByList(List<CategorySpecification> categorySpecifications) throws BaseException{
        return categorySpecificationMapper.saveByList(categorySpecifications);
    }

    /**
    * 批量物理删除
    * @param ids
    * @throws BaseException
    */
    @Override
    public Integer physicsDeleteArray(String[] ids) throws BaseException{
        return categorySpecificationMapper.physicsDeleteArray(ids);
    }
}

