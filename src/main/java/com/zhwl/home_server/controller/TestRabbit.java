package com.zhwl.home_server.controller;

import com.zhwl.home_server.bean.ResultVo;
import com.zhwl.home_server.rabbitmq.send.TestSender;
import org.apache.xmlbeans.impl.xb.ltgfmt.TestsDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.transform.Result;

@RestController
public class TestRabbit {
    @Autowired
    TestSender testSender;
    @GetMapping("testQueue/{message}")
    public ResultVo testQueue(@PathVariable String message){
        testSender.sender(message);
        System.out.println(message);
        return ResultVo.ok();
    }
}
