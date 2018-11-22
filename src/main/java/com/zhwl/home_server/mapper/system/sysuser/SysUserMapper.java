package com.zhwl.home_server.mapper.system.sysuser;

import com.zhwl.home_server.bean.Page;
import com.zhwl.home_server.bean.system.SysUser;

import java.util.List;

public interface SysUserMapper {
    List<SysUser> selectBySelective(SysUser sysUser);

    List<SysUser> selectByPage(Page pg);

    SysUser loadUserByUsername(String username);

    Integer save(SysUser sysUser);

    Integer updateBySelective(SysUser sysUser);

    Integer deleteByIds(String[] ids);

    Integer checkPhoneExist(String phone);

    Integer checkEmailExist(String email);
}
