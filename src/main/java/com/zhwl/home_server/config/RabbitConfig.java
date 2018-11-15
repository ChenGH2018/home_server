package com.zhwl.home_server.config;

import lombok.Data;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
//@ConfigurationProperties(prefix = "rabbit.properties")
public class RabbitConfig {

    public static String emailQueueName;

    @Value("${rabbit.properties.emailQueueName}")
    public void setEmailQueueName(String emailQueueName){
        RabbitConfig.emailQueueName = emailQueueName;
    }

    @Bean
    public Queue queue(){
        //邮箱队列，使用公平队列模式
        //public Queue(String name, boolean durable, boolean exclusive, boolean autoDelete)
        return new Queue(emailQueueName,true,false,true);
    }
}
