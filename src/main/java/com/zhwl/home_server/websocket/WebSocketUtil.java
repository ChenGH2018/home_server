package com.zhwl.home_server.websocket;

import com.zhwl.home_server.bean.shopaudit.ShopAudit;
import com.zhwl.home_server.bean.socketmessage.ComponentMessage;
import com.zhwl.home_server.service.shopaudit.ShopAuditService;
import com.zhwl.home_server.util.SysUserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Slf4j
@Component
public class WebSocketUtil {

    @Autowired
    private ShopAuditService shopAuditService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    public static ConcurrentMap<String, ComponentMessage> messageMaps = new ConcurrentHashMap<>();

    //发送管理员订阅消息
    public Boolean sendAdminTopicMessage(ComponentMessage message) {
        if(initComponentMessage(message)){
            messagingTemplate.convertAndSend("/topic/shopAudit", messageMaps.get(message.getComponentUrl()));
            messagingTemplate.
            return true;
        }
        return false;
    }

    public boolean initComponentMessage(ComponentMessage message) {
        String componentUrl = message.getComponentUrl();
        //根据URL作为KEY
        ComponentMessage componentMessage = messageMaps.get(componentUrl);
        if (null == componentMessage) {
            if (message.getUnprocessedMessage() >= 1) {//正确流程
                messageMaps.put(componentUrl, message);
                componentMessage = message;
            } else {
                log.error("管理员没有未处理的消息，但传了一条消息减一的请求:component:" + message.getComponent());
                return false;
            }
        } else {//map中已经有组件的消息
            componentMessage.setUnprocessedMessage(componentMessage.getUnprocessedMessage() + message.getUnprocessedMessage());
        }
        componentMessage.setTime(new Date());
        return true;
    }

    //发送审核消息
    public Boolean sendShopAuditMessage(Integer unProcessedMessage) {
        ComponentMessage componentMessage = new ComponentMessage();
        componentMessage.setUnprocessedMessage(unProcessedMessage);
        componentMessage.setComponent("businessAudit");
        componentMessage.setMenuName("商家审核");
        componentMessage.setComponentUrl("/shopAudit/**");
        componentMessage.setParentComponent("Home");
        componentMessage.setParentMenuName("商家管理");
        componentMessage.setParentComponentUrl("/");
        return sendAdminTopicMessage(componentMessage);
    }
    //审核之后消息处理
    public Boolean postShopAuditMessage(ShopAudit shopAudit){
        sendShopAuditMessage(-1);
        ShopAudit shopAudit1 = shopAuditService.selectById(shopAudit.getId());
        ComponentMessage componentMessage = new ComponentMessage();
        componentMessage.setUnprocessedMessage(1);
        componentMessage.setComponent("businessAudit");
        componentMessage.setMenuName("商家审核");
        componentMessage.setComponentUrl("/shopAudit/**");
        componentMessage.setParentComponent("Home");
        componentMessage.setParentMenuName("商家管理");
        componentMessage.setParentComponentUrl("/");
        messageMaps.put(shopAudit1.getShopBasic().getSysUser().getUsername(),);
    }
    //发送商家审核通知
    public Boolean sendShop
}
