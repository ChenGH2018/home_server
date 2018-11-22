package com.zhwl.home_server.rabbitmq.consumer;

import com.rabbitmq.client.Channel;
import com.zhwl.home_server.sysconst.QueueName;
import com.zhwl.home_server.util.MD5Utils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;


@Component
@RabbitListener(queues = QueueName.EMAIL_VALIDATE_QUEUE)
public class EmailConsumer {

    @Autowired
    JavaMailSender mailSender;

    /**
     * 邮件队列处理
     *
     * @param email
     */
    @RabbitHandler
    public void handleEmailQueue(String email, Channel channel, Message message) {
        String content0 = "请点击下面的按钮完成注册：<br>\n" +
                "<div class=\"finish-btn\" style=\"height:40px; padding:10px 0px 0px 221px;\">\n" +
                "<a target=\"_blank\" href=\"http://192.168.100.15:8888/shopManage/activateEmail?registryId=";
        String content1 = "\" style=\"width:104px; height:40px; float:left; background-color:#FFFCCD;\">\n" +
                "<img src=\"https://s.tbcdn.cn//app/uc/img/conform_email_submit.png\" style=\"border:0px;\">\n" +
                "</a>\n" +
                "</div>\n" +
                "如果您看不到上方的按钮，同样可以点击以下链接完成注册：\n" +
                "<a target=\"_blank\" href=\"http://192.168.100.15:8888/shopManage/activateEmail?registryId=";
        String content2 = "\" style=\"color:#0040D8; text-decoration:none\">http://192.168.100.15:8888/shopManage/activateEmail?registryId=";
        String content3 = "\" </a>\n </div> <div class=\"link-text\" style=\"padding:10px 0px 15px; color:#41648D\"> 也可以复制下面的链到浏览器地址栏中完成注册：<br> <span style=\"color:#000\">http://192.168.100.15:8888/shopManage/activateEmail?registryId=";
        String content4 = "</span> </div>";
        System.out.println("receive email:" + email);
        try {
            //创建多用途的邮箱消息对象  简单的邮箱消息对象只能发送文本
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            //创建邮箱消息包装类（方便操作）,如需要加附件，必须直接multipart为true
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            //发件人
            helper.setFrom("1239123910@qq.com");
            //收件人
            helper.setTo(email);
            //设置邮箱标题
            helper.setSubject("亲爱的，离注册众合未来就差一步");
            //添加html代码(第二个参数为是否用html解析)
            String md5 = MD5Utils.getMd5(email);
            StringBuilder sb = new StringBuilder();
            sb.append(content0).append(md5).append(content1).append(md5).append(content2).append(md5).append(content3).append(md5).append(content4);
            helper.setText(sb.toString(), true);
            /*让JavaMailSender mailSender 这个对象帮我们发送*/
            mailSender.send(mimeMessage);
            /*应答消息*/
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
