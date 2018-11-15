package com.zhwl.home_server.service.system;

import com.zhwl.home_server.bean.Page;
import com.zhwl.home_server.bean.system.SysUser;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface SysUserService extends UserDetailsService {
    List<SysUser> selectAll();
    Page selectByPage(Page pg);
    SysUser selectById(String id);

    Integer save(SysUser sysUser);

    Integer updateBySelective(SysUser sysUser);

    Integer deleteByIds(String[] ids);

    void updateRoles(String sysUserId, String[] roleIds);

    Integer updateSysUser(SysUser sysUser);

    List<SysUser> selectBySelective(SysUser sysUser);
}
