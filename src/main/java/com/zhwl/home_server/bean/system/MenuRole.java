package com.zhwl.home_server.bean.system;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "menu_role")
public class MenuRole {
    @Id
    private String id;
    private String menuId;
    private String roleId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
