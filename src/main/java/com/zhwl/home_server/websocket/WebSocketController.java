package com.zhwl.home_server.websocket;

import com.zhwl.home_server.bean.socketmessage.ComponentMessage;
import com.zhwl.home_server.bean.system.SysUser;
import com.zhwl.home_server.util.SysUserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class WebSocketController {

    @Autowired
    SimpMessagingTemplate messagingTemplate;

    @SubscribeMapping("/shopAudit")
    public ComponentMessage shopAuditSubscribe() {
        System.out.println("接收管理员的商家审核订阅");
        return WebSocketUtil.messageMaps.get("/shopAudit/**");
    }

    @SubscribeMapping("/shop")
    public ComponentMessage shopSubscribe(Principal ) {
        System.out.println("接收商家的订阅");
        SysUser sysUser = SysUserUtil.getNonNullUser();
        if(null == sysUser){
            log.error("接收到非商家类型的商家订阅");
            return null;
        }
        return WebSocketUtil.messageMaps.get(sysUser.getUsername());
    }
}
