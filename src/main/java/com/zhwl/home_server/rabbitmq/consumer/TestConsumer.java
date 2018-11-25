package com.zhwl.home_server.rabbitmq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.nio.channels.Channel;

@Component
@RabbitListener(queues = "testQueue")
public class TestConsumer {

    @RabbitHandler
    public void handler(String message, Channel channel){
        System.out.println(message);
    }
//    @RabbitHandler
//    public void handler(String message){
//        System.out.println(message);
//    }
}
