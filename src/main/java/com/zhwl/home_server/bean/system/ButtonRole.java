package com.zhwl.home_server.bean.system;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "button_role")
public class ButtonRole {
    @Id
    private String id;
    private String buttonId;
    private String roleId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getButtonId() {
        return buttonId;
    }

    public void setButtonId(String buttonId) {
        this.buttonId = buttonId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
