package com.zhwl.home_server.bean.socketmessage;

import lombok.Data;

import java.util.Date;

@Data
public class BasicMessage {
//    private Integer ReceiveTargetType;  //接收目标类型
//    private String ReceiveTargetName;   //接收目标名字
//    private String ReceiveTargetInfo;   //接收目标信息
    private String senderName;  //发送者名字
    private String title; //消息标题
    private String content; //消息内容
    private Date time;  //消息时间
}
