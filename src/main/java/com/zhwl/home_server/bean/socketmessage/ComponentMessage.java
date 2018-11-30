package com.zhwl.home_server.bean.socketmessage;


import lombok.Data;

import java.util.Date;

@Data
public class ComponentMessage {
    //    private Integer ReceiveTargetType;  //接收目标类型
//    private String ReceiveTargetName;   //接收目标名字
//    private String ReceiveTargetInfo;   //接收目标信息
//    private String senderName;  //发送者名字
//    private String content; //消息内容
//    private String title; //消息标题
    private String menuName;   //菜单名
    private String componentUrl;   //组件URL
    private String component;   //组件名
    private String parentMenuName;   //父菜单名
    private String parentComponentUrl;   //父组件URL
    private String parentComponent;   //父组件名
    private Integer unprocessedMessage; //未处理的消息数
    private Date time;  //消息时间
}
