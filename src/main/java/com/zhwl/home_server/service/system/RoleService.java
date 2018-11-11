package com.zhwl.home_server.service.system;

import com.zhwl.home_server.bean.Page;
import com.zhwl.home_server.bean.system.Role;

import java.util.List;

public interface RoleService {
    List<Role> selectAll();
    Page selectByPage(Page pg);
    Role selectById(String id);

    Integer save(Role role);

    Integer updateBySelective(Role role);

    Integer deleteByIds(String[] ids);

    void updateRoleMenu(String roleId, String[] menuIds);

    void updateRoleButton(String roleId, String[] buttonIds);
}
