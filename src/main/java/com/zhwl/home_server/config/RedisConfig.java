package com.zhwl.home_server.config;


import com.zhwl.home_server.util.RedisUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Redis缓存配置类
 * @author yanwensheng
 */
@Configuration
public class RedisConfig {
    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private int port;
    @Value("${spring.redis.pool.max-idle}")
    private int maxIdle;
    @Value("${spring.redis.pool.min-idle}")
    private int minIdle;

    /**
     * 1.创建JedisPoolConfig对象。在该对象中完成一些链接池配置
     * @ConfigurationProperties:会将前缀相同的内容创建一个实体。
     */
    @Bean
    public JedisPoolConfig jedisPoolConfig(){
        JedisPoolConfig config = new JedisPoolConfig();
		//最大空闲数
		config.setMaxIdle(maxIdle);
		//最小空闲数
		config.setMinIdle(minIdle);
        return config;
    }


    /**
     * 2.创建JedisConnectionFactory：配置redis链接信息
     */
    @Bean
    public JedisConnectionFactory jedisConnectionFactory(JedisPoolConfig config){
        JedisConnectionFactory factory = new JedisConnectionFactory();
        //关联链接池的配置对象
        factory.setPoolConfig(config);
        //配置链接Redis的信息
        //主机地址
		factory.setHostName(host);
		//端口
		factory.setPort(port);
        return factory;
    }

    /**
     * 3.创建RedisTemplate:用于执行Redis操作的方法
     */
    @Bean
    public RedisTemplate<String,Object> redisTemplate(JedisConnectionFactory factory){
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        initDomainRedisTemplate(template, factory);
        return template;
    }

    /**
     * 4.设置数据存入 redis 的序列化方式,并开启事务
     */
    private void initDomainRedisTemplate(RedisTemplate<String, Object> redisTemplate, RedisConnectionFactory factory) {
        //如果不配置Serializer，那么存储的时候缺省使用String，如果用User类型存储，那么会提示错误User can't cast to String！
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer(Object.class));
        // 开启事务
        redisTemplate.setEnableTransactionSupport(true);
        redisTemplate.setConnectionFactory(factory);
    }

    /**
     * 5.注入封装RedisTemplate
     */
    @Bean(name = "redisUtil")
    public RedisUtil redisUtil(RedisTemplate<String, Object> redisTemplate) {
        RedisUtil redisUtil = new RedisUtil();
        redisUtil.setRedisTemplate(redisTemplate);
        return redisUtil;
    }
}
