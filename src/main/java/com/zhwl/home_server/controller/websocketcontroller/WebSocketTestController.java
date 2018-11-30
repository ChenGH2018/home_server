package com.zhwl.home_server.controller.websocketcontroller;

import com.zhwl.home_server.bean.ResultVo;
import com.zhwl.home_server.websocket.WebSocketUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("webSocketTest")
public class WebSocketTestController {

    @Autowired
    private WebSocketUtil webScoketUtil;
    @ApiOperation(value = "添加指定数量的未审核消息给管理员", notes = "添加指定数量的未审核消息给管理员")
    @GetMapping(value = "/sendAddShopAuditMessage/{quantity}")
    public ResultVo sendAddShopAuditMessage(@PathVariable Integer quantity){
        for(int i = 0; i<quantity; i++)
            webScoketUtil.sendShopAuditMessage(1);
        return ResultVo.ok("发送成功：发送了"+quantity+"条");
    }
}
