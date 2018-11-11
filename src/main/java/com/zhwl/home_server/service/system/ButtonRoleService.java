package com.zhwl.home_server.service.system;

import com.zhwl.home_server.bean.system.ButtonRole;

import java.util.List;

public interface ButtonRoleService {
    List<String> getButtonIdByRoleId(String roleId);

    Integer deleteByRoleId(String roleId);

    Integer saveByList(List<ButtonRole> list);
}
