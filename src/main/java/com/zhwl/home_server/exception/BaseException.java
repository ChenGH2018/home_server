package com.zhwl.home_server.exception;


import com.zhwl.home_server.system.SysEnum;

public class BaseException extends RuntimeException {
    private Integer code;

    public BaseException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public BaseException(SysEnum sysEnum) {
        super(sysEnum.getMsg());
        this.code = sysEnum.getCode();
    }


}
