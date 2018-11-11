package com.zhwl.home_server.bean.system;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "sys_user_role")
public class SysUserRole {
    @Id
    private String id;
    private String sysUserId;
    private String roleId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSysUserId() {
        return sysUserId;
    }

    public void setSysUserId(String sysUserId) {
        this.sysUserId = sysUserId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
