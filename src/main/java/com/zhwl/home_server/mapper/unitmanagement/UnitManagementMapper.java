package com.zhwl.home_server.mapper.unitmanagement;
import com.zhwl.home_server.bean.unitmanagement.UnitManagement;
import com.zhwl.home_server.bean.Page;
import java.util.List;

/**
 * 说明： 价钱单位管理
 * 创建人：chenguihao
 * 创建时间：2018-11-27
 */
public interface UnitManagementMapper {

    Integer save(UnitManagement unitManagement);

    Integer saveByList(List<UnitManagement> unitManagements);

    Integer updateBySelective(UnitManagement unitManagement);

    Integer deleteOne(String id);

    List<UnitManagement> selectBySelective(UnitManagement unitManagement);

    List<UnitManagement> getUnitManagementByPage(Page pg);

    Integer physicsDeleteArray(String[] ids);

    Integer softDeleteArray(String[] ids);
}
