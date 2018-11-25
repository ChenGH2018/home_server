package com.zhwl.home_server.rabbitmq.send;

import com.zhwl.home_server.config.RabbitConfig;
import lombok.Data;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Data
public class TestSender implements RabbitTemplate.ConfirmCallback{
    private static final String QUEUE_NAME = RabbitConfig.emailQueueName;

    @Autowired
    private RabbitTemplate rabbitTemplate;//SCOPE:PROTOTYPE
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if(ack){
            System.out.println("email arrive queue!");
        }else {
            System.out.println("email sendValidate failed:"+cause);
        }
    }

    public boolean sender(String message){
        rabbitTemplate.setConfirmCallback(this);//到达回调当前对象的confim方法
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());//消息ID存放类
        System.out.println("callbackSender UUID: " + correlationData.getId());
        rabbitTemplate.convertAndSend("",QUEUE_NAME,message,correlationData);//匿名交换机发送，直接发送到队列名
        return true;
    }
}
