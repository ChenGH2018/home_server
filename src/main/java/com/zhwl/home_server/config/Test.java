package com.zhwl.home_server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Test {
    @Autowired
    private RabbitConfig rabbitConfig;

    public Test() throws InterruptedException {
//        System.out.println(rabbitConfig.getEmailQueueName());
//        Thread.sleep(5000);
    }
}
