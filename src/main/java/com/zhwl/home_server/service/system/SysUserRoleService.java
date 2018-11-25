package com.zhwl.home_server.service.system;

import com.zhwl.home_server.bean.system.SysUserRole;

import java.util.HashMap;
import java.util.List;

public interface SysUserRoleService {
    Integer saveByList(List<SysUserRole> list);

    Integer deleteBySysUserId(String id);

    Integer updateRole(HashMap<String, Object> map);
}
