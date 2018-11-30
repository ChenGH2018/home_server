package com.zhwl.home_server.config;

import com.zhwl.home_server.bean.shopaudit.ShopAudit;
import com.zhwl.home_server.bean.socketmessage.ComponentMessage;
import com.zhwl.home_server.service.shopaudit.ShopAuditService;
import com.zhwl.home_server.websocket.WebSocketUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ApplicationRunnerInit implements ApplicationRunner{

    @Autowired
    ShopAuditService shopAuditService;
    @Autowired
    WebSocketUtil webSocketUtil;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        initUnAuditedQuantity();
    }

    private void initUnAuditedQuantity() {
        ShopAudit shopAudit = new ShopAudit();
        shopAudit.setAuditStatus(0);
        List<ShopAudit> shopAudits = shopAuditService.selectBySelective(shopAudit);
        if(null != shopAudits && !shopAudits.isEmpty()){
            ComponentMessage componentMessage = new ComponentMessage();
            componentMessage.setUnprocessedMessage(shopAudits.size());
            componentMessage.setComponent("businessAudit");
            componentMessage.setMenuName("商家审核");
            componentMessage.setComponentUrl("/shopAudit/**");
            componentMessage.setParentComponent("Home");
            componentMessage.setParentMenuName("商家管理");
            componentMessage.setParentComponentUrl("/");
            webSocketUtil.initComponentMessage(componentMessage);
        }
    }
}
