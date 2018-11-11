package com.zhwl.home_server.system;

public enum SysEnum {

    USERNAMENULL(0,"用户名为空"),
    USERNAMEEXIST(1,"用户名已存在"),
    PASSWORDNULL(2,"密码为空"),
    ROLENAMENULL(3,"角色英文名为空"),
    ROLENAMEEXIST(4,"角色英文名已存在"),
    VALIDATECODEERROR(5,"验证码错误"),
    BUTTONNUMBERNULL(6,"按钮编号为空"),
    BUTTONNUMBEREXIST(7,"按钮编号已存在"),
    ;

    private Integer code;
    private String msg;

    SysEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
