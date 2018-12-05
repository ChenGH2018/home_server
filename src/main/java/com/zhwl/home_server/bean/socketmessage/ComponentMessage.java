package com.zhwl.home_server.bean.socketmessage;


import lombok.Data;

import java.util.Date;

@Data
public class ComponentMessage extends BasicMessage{

    private String menuName;   //菜单名
    private String componentUrl;   //组件URL
    private String component;   //组件名
    private String parentMenuName;   //父菜单名
    private String parentComponentUrl;   //父组件URL
    private String parentComponent;   //父组件名
    private Integer unprocessedMessage; //未处理的消息数
    private Date time;  //消息时间
}
