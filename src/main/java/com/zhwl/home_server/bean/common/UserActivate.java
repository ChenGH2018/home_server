package com.zhwl.home_server.bean.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserActivate implements Serializable {
    private String email;
    private Boolean emailActivate;
    private String phone;
    private Boolean phoneActivate;
}
