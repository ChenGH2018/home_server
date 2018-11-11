package com.zhwl.home_server.mapper.system.menu_role;


import com.zhwl.home_server.bean.system.MenuRole;

import java.util.List;

public interface MenuRoleMapper {
    List<String> getMenuIdByRoleId(String roleId);

    Integer saveByList(List<MenuRole> list);

    Integer deleteByRoleId(String roleId);
}
