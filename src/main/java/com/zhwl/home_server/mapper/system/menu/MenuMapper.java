package com.zhwl.home_server.mapper.system.menu;

import com.zhwl.home_server.bean.Page;
import com.zhwl.home_server.bean.system.Menu;

import java.util.List;


public interface MenuMapper {
    List<Menu> selectBySelective(Menu menu);
    List<Menu> selectByPage(Page pg);

    Integer save(Menu menu);

    Integer updateBySelective(Menu menu);

    Integer deleteByIds(String[] ids);

    List<Menu> menuTree();

    List<Menu> selectBySysUserId(String userId);

    List<Menu> getMenuButtons();
}
