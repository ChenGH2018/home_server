package com.zhwl.home_server.service.shop.impl;

import com.google.common.base.Strings;
import com.zhwl.home_server.bean.shopcomplete.ShopComplete;
import com.zhwl.home_server.bean.system.SysUser;
import com.zhwl.home_server.config.RabbitConfig;
import com.zhwl.home_server.exception.BaseException;
import com.zhwl.home_server.service.shop.ShopBasicService;
import com.zhwl.home_server.service.shop.ShopManageService;
import com.zhwl.home_server.service.system.SysUserService;
import com.zhwl.home_server.util.MD5Utils;
import com.zhwl.home_server.util.RedisUtil;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;

public class ShopManageServiceImpl implements ShopManageService {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private ShopBasicService shopBasicService;
    @Autowired
    RabbitTemplate rabbitTemplate;
    @Autowired
    RedisUtil redisUtil;
    /**
     * 查询sys_user表的用户名是否包含该邮箱账号
     * @param email
     * @return true:存在 false：不存在
     */
    @Override
    public boolean checkShopEmail(String email) {
        if(Strings.isNullOrEmpty(email)) throw new BaseException("邮箱账号不能为空");
        SysUser sysUser = new SysUser();
        sysUser.setUsername(email);
        return sysUserService.selectBySelective(sysUser).size() != 0 ;
    }

    @Override
    public boolean registry(ShopComplete shopComplete) {

        return false;
    }

    @Override
    public boolean sendEmailValidate(String email) {
        if(Strings.isNullOrEmpty(email)) throw new BaseException("send Email failed: Email不能为空");
        //发送邮箱
        rabbitTemplate.convertAndSend(RabbitConfig.emailQueueName,email);
        //将邮箱和key存放至redis
        String md5 = MD5Utils.getMd5(email);
        HashMap<String, Object> map = new HashMap<>();
        map.put(md5,email);
        return redisUtil.hset("RegEmailMap",md5,email,60*60*12);//24小时有效期
    }
}
