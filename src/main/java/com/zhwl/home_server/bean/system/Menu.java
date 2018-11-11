package com.zhwl.home_server.bean.system;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Id;
import javax.persistence.Table;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Table(name = "menu")
public class Menu {
    @Id
    private String id;
    private String menuName; //菜单名称
    private String url; //链接
    private String component; //组件
    private String iconCls; //图标
    private String parentId;  //父Id
    private String menuOrder;  //菜单序号
    @ApiModelProperty(hidden = true)
    private Date addTime; //添加时间

    @ApiModelProperty(hidden = true)
    private List<Menu> children;
    @ApiModelProperty(hidden = true)
    private List<Role> roles;
    @ApiModelProperty(hidden = true)
    private List<Button> buttons;

    public List<Button> getButtons() {
        return buttons;
    }

    public void setButtons(List<Button> buttons) {
        this.buttons = buttons;
    }

    @ApiModelProperty(hidden = true)
    private boolean disabled;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getMenuOrder() {
        return menuOrder;
    }

    public void setMenuOrder(String menuOrder) {
        this.menuOrder = menuOrder;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getAddTimeToString() {
        return addTime == null ? "" : new SimpleDateFormat("yyyy-MM-dd").format(addTime);
    }

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }
}
