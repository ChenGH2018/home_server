package com.zhwl.home_server.mapper.system.sysuser_role;

import com.zhwl.home_server.bean.system.SysUserRole;

import java.util.HashMap;
import java.util.List;


public interface SysUserRoleMapper {
    Integer saveByList(List<SysUserRole> list);
    Integer save(SysUserRole sysUserRole);
    Integer deleteBySysUserId(String sysUserId);

    Integer updateRole(HashMap<String, Object> map);
}
