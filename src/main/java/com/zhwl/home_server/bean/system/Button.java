package com.zhwl.home_server.bean.system;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Id;
import javax.persistence.Table;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by admin on 2017/12/29.
 */
@Table(name = "button")
public class Button {
    @Id
    private String id;
    private String buttonNumber;//按钮编号
    private String buttonName;//按钮名称
    private String url;//url
    private String requestMethod; //请求方式
    private String menuId;//菜单Id
    @ApiModelProperty(hidden = true)
    private Date addTime;//添加时间

    @ApiModelProperty(hidden = true)
    private Menu menu;
    @ApiModelProperty(hidden = true)
    private List<Role> roles;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getButtonNumber() {
        return buttonNumber;
    }

    public void setButtonNumber(String buttonNumber) {
        this.buttonNumber = buttonNumber;
    }

    public String getButtonName() {
        return buttonName;
    }

    public void setButtonName(String buttonName) {
        this.buttonName = buttonName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
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

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
