package com.zhwl.home_server.mapper.system.role;

import com.zhwl.home_server.bean.Page;
import com.zhwl.home_server.bean.system.Role;

import java.util.List;

public interface RoleMapper {
    List<Role> selectBySelective(Role role);
    List<Role> selectByPage(Page pg);
    boolean exitByName(String name);

    Integer save(Role role);

    Integer updateBySelective(Role role);

    Integer deleteByIds(String[] ids);
}
