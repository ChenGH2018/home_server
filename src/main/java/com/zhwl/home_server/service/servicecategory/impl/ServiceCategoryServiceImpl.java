package com.zhwl.home_server.service.servicecategory.impl;

import com.google.common.base.Strings;
import com.zhwl.home_server.bean.Page;
import com.zhwl.home_server.bean.servicecategory.ServiceCategory;
import com.zhwl.home_server.exception.BaseException;
import com.zhwl.home_server.mapper.servicecategory.ServiceCategoryMapper;
import com.zhwl.home_server.service.servicecategory.ServiceCategoryService;
import com.zhwl.home_server.util.QiNiuUtil;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * 说明： 服务类别管理
 * 创建人：chenguihao
 * 创建时间：2018-11-23
 */

@Service
@Transactional
@Log4j
public class ServiceCategoryServiceImpl implements ServiceCategoryService {

    @Autowired
    ServiceCategoryMapper serviceCategoryMapper;

    @Override
    public Integer save(ServiceCategory serviceCategory) throws BaseException {
        serviceCategory.setIsDelete(0);
        return serviceCategoryMapper.save(serviceCategory);
    }

    @Override
    public Integer deleteOne(String serviceCategoryId) throws BaseException {
        return serviceCategoryMapper.deleteOne(serviceCategoryId);
    }

    @Override
    public Integer updateBySelective(ServiceCategory serviceCategory) throws BaseException {
        if (Strings.isNullOrEmpty(serviceCategory.getId())) throw new RuntimeException("要修改的ID不能为空");
        return serviceCategoryMapper.updateBySelective(serviceCategory);
    }


    @Override
    public ServiceCategory selectById(String id) throws BaseException {
        ServiceCategory serviceCategory = new ServiceCategory();
        serviceCategory.setId(id);
        List<ServiceCategory> serviceCategorys = serviceCategoryMapper.selectBySelective(serviceCategory);
        return serviceCategorys != null && serviceCategorys.size() == 1 ? serviceCategorys.get(0) : null;
    }

    @Override
    public List<ServiceCategory> selectBySelective(ServiceCategory serviceCategory) throws BaseException {
        List<ServiceCategory> serviceCategories = serviceCategoryMapper.selectBySelective(serviceCategory);
        analysisKey(serviceCategories);
        return serviceCategories;
    }

    @Override
    public List<ServiceCategory> getServiceCategoryByPage(Page pg) throws BaseException {
        List<ServiceCategory> serviceCategoryByPage = serviceCategoryMapper.getServiceCategoryByPage(pg);
        analysisKey(serviceCategoryByPage);
        return serviceCategoryByPage;
    }

    @Override
    public Integer saveByList(List<ServiceCategory> serviceCategorys) throws BaseException {
        return serviceCategoryMapper.saveByList(serviceCategorys);
    }

    /**
     * 批量物理删除
     *
     * @param ids
     * @throws BaseException
     */
    @Override
    public Integer physicsDeleteArray(String[] ids) throws BaseException {
        return serviceCategoryMapper.physicsDeleteArray(ids);
    }

    /**
     * 批量软删除
     *
     * @param ids
     * @throws BaseException
     */
    public Integer softDeleteArray(String[] ids) throws BaseException {
        return serviceCategoryMapper.softDeleteArray(ids);
    }
    /**
     * 解析private空间的图片key，生成访问的url
     */
    public Boolean analysisKey(List<ServiceCategory> serviceCategories){
        serviceCategories.stream().parallel().forEach(it -> {
            if (!Strings.isNullOrEmpty(it.getCategoryImgKey())) {
                try {
                    String[] categoryImgKeys = it.getCategoryImgKey().split(",");
                    it.setCategoryImg("");
                    for(int i = 0 ;i<categoryImgKeys.length;i++){
                        it.setCategoryImg(it.getCategoryImg()+QiNiuUtil.getDownloadUrl(categoryImgKeys[i])+(i==categoryImgKeys.length-1? "":","));
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

            }
        });
        return true;
    }
}

