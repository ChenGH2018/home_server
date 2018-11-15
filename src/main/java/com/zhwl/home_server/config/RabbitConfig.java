package com.zhwl.home_server.config;

import lombok.Data;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Data
@Configuration
//@ConfigurationProperties(prefix = "rabbit.properties")
public class RabbitConfig {

    public static String emailQueueName;

    @Value("${rabbit.properties.emailQueueName}")
    public void setEmailQueueName(String emailQueueName) {
        RabbitConfig.emailQueueName = emailQueueName;
    }

    @Bean
    public Queue queue() {
        //邮箱队列，使用公平队列模式
        //public Queue(String name, boolean durable, boolean exclusive, boolean autoDelete)
        return new Queue(emailQueueName, true, false, true);
    }


    /**
     * 基础配置
     */
    @Value("${spring.rabbitmq.host}")
    private String addresses;

    @Value("${spring.rabbitmq.port}")
    private String port;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

    @Value("${spring.rabbitmq.virtual-host}")
    private String virtualHost;

    @Value("${spring.rabbitmq.publisher-confirms}")
    private boolean publisherConfirms;

    @Bean
    public ConnectionFactory connectionFactory() {

        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setAddresses(addresses+":"+port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost(virtualHost);
        /** 如果要进行消息回调，则这里必须要设置为true */
        connectionFactory.setPublisherConfirms(publisherConfirms);
        return connectionFactory;
    }

    @Bean
    /**RabbitTemplate设置为多例模式，这样confirm类也不同
      因为要设置回调类，所以应是prototype类型，如果是singleton类型，则回调类为最后一次设置*/
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RabbitTemplate rabbitTemplatenew() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        return template;
    }
}
