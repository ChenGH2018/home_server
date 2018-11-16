package com.zhwl.home_server.service.shop.impl;

import com.google.common.base.Strings;
import com.zhwl.home_server.bean.common.UserActivate;
import com.zhwl.home_server.bean.shop.ShopBasic;
import com.zhwl.home_server.bean.shop.ShopRegistry;
import com.zhwl.home_server.bean.system.SysUser;
import com.zhwl.home_server.exception.BaseException;
import com.zhwl.home_server.rabbitmq.send.EmailSender;
import com.zhwl.home_server.service.shop.ShopBasicService;
import com.zhwl.home_server.service.shop.ShopManageService;
import com.zhwl.home_server.service.system.SysUserService;
import com.zhwl.home_server.sysconst.RedisKey;
import com.zhwl.home_server.util.MD5Utils;
import com.zhwl.home_server.util.RedisUtil;
import com.zhwl.home_server.util.Tools;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ShopManageServiceImpl implements ShopManageService {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private ShopBasicService shopBasicService;
    @Autowired
    RabbitTemplate rabbitTemplate;
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    EmailSender emailSender;
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

    /**
     * 注册模块、 true：注册成功 false：注册失败
     * @param shopRegistry
     * @return
     */
    @Override
    public boolean registry(ShopRegistry shopRegistry) {
        shopRegistryValite(shopRegistry);
//        redisUtil.hget(RedisKey.REG_EMAIL_MAP,MD5Utils.getMd5(sho))
        return false;
    }

    private void shopRegistryValite(ShopRegistry shopRegistry) {
        ShopBasic shopBasic = shopRegistry.getShopBasic();
        SysUser sysUser = shopRegistry.getSysUser();
        if(null == shopBasic || null == sysUser ) throw new BaseException("参数异常");
        //sysUser验证
        if(sysUser.getPassword().trim().length()<=8)throw new BaseException("密码不足8位，请重新输入");
//        if(Strings.isNullOrEmpty(shopBasic.getEmail()) ||Strings.isNullOrEmpty(shopBasic.getEnterpriseName()||
//                Strings.isNullOrEmpty(shopBasic.getContactPhone()||)))
    }

    @Override
    public boolean sendEmailValidate(String email) {
        if(Strings.isNullOrEmpty(email)) throw new BaseException("sendValidate Email failed: Email不能为空");
        //发送邮箱
        emailSender.sendValidate(email);
        //将邮箱和key存放至redis
        String md5 = MD5Utils.getMd5(email);
        UserActivate emailActivate = new UserActivate();
        emailActivate.setEmailActivate(false);
        emailActivate.setEmail(email);
        redisUtil.hset(RedisKey.REG_EMAIL_MAP,md5,emailActivate,60*60*12);//24小时有效期
        System.out.println("邮箱发送成功");
        return true;
    }

    @Override
    public void activateEmail(String registryId) {
        if(Strings.isNullOrEmpty(registryId)) throw new BaseException("activate Email failed: registryId不能为空");
        //查询是否含有该注册ID，不是则是假ID或已过期
        UserActivate userActivate = (UserActivate) redisUtil.hget(RedisKey.REG_EMAIL_MAP, registryId);
        if(null == userActivate) throw new BaseException("该邮件已过期。");
        //查询是否含有该邮箱名
        if(checkShopEmail(userActivate.getEmail())) throw new BaseException("邮箱已作为用户名存在");
        //验证成功，设置为激活
        userActivate.setEmailActivate(true);
        redisUtil.hset(RedisKey.REG_EMAIL_MAP,registryId,userActivate);
        //返回注册页面填充数据
    }

    @Override
    public boolean phoneValidate(Map<String, String> map) {
        String email =map.get("email");
        String phone = map.get("phone");
        if(Strings.isNullOrEmpty(email)||Strings.isNullOrEmpty(phone)) throw new BaseException("参数异常");
        if(!Tools.isPhone(phone)) throw new BaseException("手机号不正确");
        //从缓存中取该用户的激活信息
        UserActivate userActivate = (UserActivate) redisUtil.hget(RedisKey.REG_EMAIL_MAP, MD5Utils.getMd5(email));
        if(null == userActivate) throw new BaseException("异常操作");

        return false;
    }
}
