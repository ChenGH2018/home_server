package com.zhwl.home_server.mapper.system.sysuser_role;

import com.zhwl.home_server.bean.system.SysUserRole;

import java.util.List;


public interface SysUserRoleMapper {
    Integer saveByList(List<SysUserRole> list);
    Integer deleteBySysUserId(String sysUserId);
}
