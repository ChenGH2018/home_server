package com.zhwl.home_server.service.system.impl;

import com.zhwl.home_server.bean.system.Menu;
import com.zhwl.home_server.exception.BaseException;
import com.zhwl.home_server.mapper.system.menu.MenuMapper;
import com.zhwl.home_server.service.system.ButtonRoleService;
import com.zhwl.home_server.service.system.MenuRoleService;
import com.zhwl.home_server.service.system.MenuService;
import com.zhwl.home_server.util.SysUserUtil;
import com.zhwl.home_server.util.UuidUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service("menuServiceImpl")
@Transactional
public class MenuServiceImpl implements MenuService {

    @Resource(name = "menuMapper")
    private MenuMapper menuMapper;
    @Resource(name = "menuRoleServiceImpl")
    private MenuRoleService menuRoleService;
    @Resource(name = "buttonRoleServiceImpl")
    private ButtonRoleService buttonRoleService;

    @Override
    public List<Menu> getAll() {
        return menuMapper.selectBySelective(null);
    }

    @Override
    public List<Menu> selectByParentId(String parentId) {
        Menu menu = new Menu();
        menu.setParentId(parentId);
        return menuMapper.selectBySelective(menu);
    }

    @Override
    public List<Menu> selectBySysUserId() {
        String userId = SysUserUtil.getCurrentUser().getId();
        if (userId == null)
            throw new BaseException(0,"无权限访问");
        return menuMapper.selectBySysUserId(userId);
    }

    @Override
    public Map<String,Object> menuTree(String roleId) {
        Map<String,Object> map = new HashMap<>();
        map.put("menus",menuMapper.menuTree());
        map.put("mids",menuRoleService.getMenuIdByRoleId(roleId));
        return map;
    }

    @Override
    public List<Menu> getMenuTree() {
        List<Menu> list = menuMapper.menuTree();
        return getMenuTreeDisable(list);
    }

    private List<Menu> getMenuTreeDisable(List<Menu> list) {
        for (Menu menu:list){
            String parentId = menu.getParentId();
            if (parentId == null || "1".equals(parentId)){
                menu.setDisabled(true);
                getMenuTreeDisable(menu.getChildren());
            }
        }
        return list;
    }

    @Override
    public List<Menu>  getMenuButtons() {
        return menuMapper.getMenuButtons();
    }
    @Override
    public List<String> getButtonIdByRoleId(String roleId) {
        return buttonRoleService.getButtonIdByRoleId(roleId);
    }

    @Override
    public Menu selectById(String id) {
        return Optional.ofNullable(id).map(x->{
            Menu menu = new Menu();
            menu.setId(x);
            List<Menu> list = menuMapper.selectBySelective(menu);
            return list != null && list.size() == 1 ? list.get(0) : null;
        }).orElse(null);
    }

    @Override
    public Integer save(Menu menu) {
        menu.setId(UuidUtil.get32UUID());
        menu.setAddTime(new Date());
        return menuMapper.save(menu);
    }

    @Override
    public Integer updateBySelective(Menu menu) {
        return menuMapper.updateBySelective(menu);
    }

    @Override
    public Integer deleteByIds(String[] ids) {
        for (String id : ids){
            if(selectByParentId(id).size()>0)
                throw new BaseException(0,"该目录含有子目录，不可直接删除，请先清空子目录");
        }
        return menuMapper.deleteByIds(ids);
    }
}
