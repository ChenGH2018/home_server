package com.zhwl.home_server.service.system;

import com.zhwl.home_server.bean.system.Menu;

import java.util.List;
import java.util.Map;

public interface MenuService {
    List<Menu> selectByParentId(String parentId);
    Map<String,Object> menuTree(String roleId);
    Menu selectById(String id);

    Integer save(Menu menu);

    Integer updateBySelective(Menu menu);

    Integer deleteByIds(String[] ids);

    List<Menu> selectBySysUserId();

    List<Menu> getAll();

    List<Menu> getMenuTree();

    List<Menu> getMenuButtons();

    List<String> getButtonIdByRoleId(String roleId);
}
