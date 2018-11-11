package com.zhwl.home_server.service.system;

import com.zhwl.home_server.bean.system.MenuRole;

import java.util.List;

public interface MenuRoleService {
    List<String> getMenuIdByRoleId(String roleId);

    Integer deleteByRoleId(String roleId);

    Integer saveByList(List<MenuRole> list);
}
