package com.zhwl.home_server.mapper.system.button_role;


import com.zhwl.home_server.bean.system.ButtonRole;

import java.util.List;

public interface ButtonRoleMapper {
    List<String> getButtonIdByRoleId(String roleId);

    Integer saveByList(List<ButtonRole> list);

    Integer deleteByRoleId(String roleId);
}
