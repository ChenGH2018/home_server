package com.zhwl.home_server;

import com.zhwl.home_server.rabbitmq.send.EmailSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HomeServerApplicationTests {
    @Autowired
    EmailSender emailSender;

    @Test
    public void contextLoads() {
        emailSender.sendValidate("chenguihao21@163.com");
    }

}
