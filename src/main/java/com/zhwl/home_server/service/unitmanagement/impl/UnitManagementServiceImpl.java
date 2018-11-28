package com.zhwl.home_server.service.unitmanagement.impl;

import com.google.common.base.Strings;
import com.zhwl.home_server.bean.Page;
import com.zhwl.home_server.bean.unitmanagement.UnitManagement;
import com.zhwl.home_server.exception.BaseException;
import com.zhwl.home_server.mapper.unitmanagement.UnitManagementMapper;
import com.zhwl.home_server.service.unitmanagement.UnitManagementService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 说明： 价钱单位管理
 * 创建人：chenguihao
 * 创建时间：2018-11-27
 */

@Service
@Transactional
@Log4j
public class UnitManagementServiceImpl implements UnitManagementService {

    @Autowired
    UnitManagementMapper unitManagementMapper;

    @Override
    public Integer save(UnitManagement unitManagement) throws BaseException {
        return unitManagementMapper.save(unitManagement);
    }

    @Override
    public Integer deleteOne(String unitManagementId) throws BaseException {
        return unitManagementMapper.deleteOne(unitManagementId);
    }

    @Override
    public Integer updateBySelective(UnitManagement unitManagement) throws BaseException {
        if(Strings.isNullOrEmpty(unitManagement.getId())) throw new RuntimeException("要修改的ID不能为空");
        return unitManagementMapper.updateBySelective(unitManagement);
    }


    @Override
    public UnitManagement selectById(String id) throws BaseException{
        UnitManagement unitManagement = new UnitManagement();
        unitManagement.setId(id);
        List<UnitManagement> unitManagements = unitManagementMapper.selectBySelective(unitManagement);
        return unitManagements != null && unitManagements.size() == 1 ? unitManagements.get(0) : null;
    }

    @Override
    public List<UnitManagement> selectBySelective(UnitManagement unitManagement) throws BaseException{
        return unitManagementMapper.selectBySelective(unitManagement);

    }

    @Override
    public List<UnitManagement> getUnitManagementByPage(Page pg) throws BaseException{
        return unitManagementMapper.getUnitManagementByPage(pg);
    }

    @Override
    public Integer saveByList(List<UnitManagement> unitManagements) throws BaseException{
        return unitManagementMapper.saveByList(unitManagements);
    }

    /**
    * 批量物理删除
    * @param ids
    * @throws BaseException
    */
    @Override
    public Integer physicsDeleteArray(String[] ids) throws BaseException{
        return unitManagementMapper.physicsDeleteArray(ids);
    }
}

