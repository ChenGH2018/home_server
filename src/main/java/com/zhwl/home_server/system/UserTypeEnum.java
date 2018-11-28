package com.zhwl.home_server.system;

public enum UserTypeEnum {
    SYSTEMUSER(1,"系统用户"),
    SHOPUSER(2,"商家用户"),
    CLIENT(3,"客户"),
    CS(4,"客服"),;//Customer Services

    private Integer type;
    private String name;

    UserTypeEnum(Integer type,String name) {
        this.type = type;
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
