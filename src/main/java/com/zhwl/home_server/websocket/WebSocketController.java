package com.zhwl.home_server.websocket;

import com.zhwl.home_server.bean.socketmessage.ComponentMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    @Autowired
    SimpMessagingTemplate messagingTemplate;

    @SubscribeMapping("/shopAudit")
    public ComponentMessage subscribeTest() {
        System.out.println("接收管理员的商家审核订阅");
        return WebSocketUtil.messageMaps.get("/shopAudit/**");
    }
}
