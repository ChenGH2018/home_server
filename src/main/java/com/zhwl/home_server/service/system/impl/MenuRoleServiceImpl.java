package com.zhwl.home_server.service.system.impl;

import com.zhwl.home_server.bean.system.MenuRole;
import com.zhwl.home_server.mapper.system.menu_role.MenuRoleMapper;
import com.zhwl.home_server.service.system.MenuRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("menuRoleServiceImpl")
@Transactional
public class MenuRoleServiceImpl implements MenuRoleService {

    @Resource(name = "menuRoleMapper")
    private MenuRoleMapper menuRoleMapper;

    @Override
    public List<String> getMenuIdByRoleId(String roleId) {
        return menuRoleMapper.getMenuIdByRoleId(roleId);
    }

    @Override
    public Integer deleteByRoleId(String roleId) {
        return menuRoleMapper.deleteByRoleId(roleId);
    }

    @Override
    public Integer saveByList(List<MenuRole> list) {
        return menuRoleMapper.saveByList(list);
    }
}
