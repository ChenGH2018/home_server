package com.zhwl.home_server.service.shop.impl;

import com.google.common.base.Strings;
import com.zhwl.home_server.bean.ResultVo;
import com.zhwl.home_server.bean.common.UserActivate;
import com.zhwl.home_server.bean.shop.ShopBasic;
import com.zhwl.home_server.bean.shop.ShopRegistry;
import com.zhwl.home_server.bean.system.SysUser;
import com.zhwl.home_server.bean.system.SysUserRole;
import com.zhwl.home_server.exception.BaseException;
import com.zhwl.home_server.rabbitmq.send.EmailSender;
import com.zhwl.home_server.service.shop.ShopBasicService;
import com.zhwl.home_server.service.shop.ShopManageService;
import com.zhwl.home_server.service.system.SysUserRoleService;
import com.zhwl.home_server.service.system.SysUserService;
import com.zhwl.home_server.sysconst.RedisKey;
import com.zhwl.home_server.system.UserTypeEnum;
import com.zhwl.home_server.util.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;

@Service
@Transactional
public class ShopManageServiceImpl implements ShopManageService {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserRoleService sysUserRoleService;
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
     *
     * @param email
     * @return true:存在 false：不存在
     */
    @Override
    public boolean checkEmailExist(String email) {
        if (Strings.isNullOrEmpty(email)) throw new BaseException("邮箱不能为空");
        return sysUserService.checkEmailExist(email);
    }

    @Override
    public boolean checkPhoneExist(String phone) {
        if (Strings.isNullOrEmpty(phone)) throw new BaseException("手机号不能为空");
        return sysUserService.checkPhoneExist(phone);
    }

    /**
     * 注册模块、 true：注册成功 false：注册失败
     *
     * @param shopRegistry
     * @return
     */
    @Override
    public boolean registry(ShopRegistry shopRegistry) {
        ShopBasic shopBasic = shopRegistry.getShopBasic();
        SysUser sysUser = shopRegistry.getSysUser();
        shopRegistryValidate(shopRegistry);
        UserActivate userActivate = (UserActivate) redisUtil.hget(RedisKey.REG_EMAIL_MAP, MD5Utils.getMd5(shopBasic.getEmail()));
        if (null == userActivate || !userActivate.getEmailActivate() || !userActivate.getPhoneActivate()) {
            throw new BaseException("该用户尚未激活");
        }
        sysUser.setId(UuidUtil.get32UUID());
        sysUser.setUsername(shopBasic.getEmail());//邮箱作为用户名
        sysUser.setUserType(UserTypeEnum.SHOPUSER.ordinal());//用户类型
        sysUser.setAddTime(new Date());
//        sysUser.setIsFreeze(0); //未冻结
//        sysUser.setIsLogout(0); //未注销
        shopBasic.setId(UuidUtil.get32UUID());
        shopBasic.setAuditStatus(0);//为审核
        shopBasic.setRegisterTime(new Date());
        shopBasic.setSysUserId(sysUser.getId());
        shopBasic.setContactPhone(userActivate.getPhone());
        if(sysUserService.save(sysUser) == 0 ||shopBasicService.save(shopBasic) == 0) throw new BaseException("系统错误");
        SysUserRole sysUserRole = new SysUserRole();
        sysUserRole.setId(UuidUtil.get32UUID());
        sysUserRole.setSysUserId(sysUser.getId());
        sysUserRole.setRoleId("a8f0957951bb4ce09e04d533c70b3659");
        if(sysUserRoleService.save(sysUserRole) == 0 ) throw new BaseException("角色分配失败");
        return true;
    }

    private void shopRegistryValidate(ShopRegistry shopRegistry) {
        ShopBasic shopBasic = shopRegistry.getShopBasic();
        SysUser sysUser = shopRegistry.getSysUser();
        if (null == shopBasic || null == sysUser) throw new BaseException("参数异常");
        //sysUser验证
        if (Strings.isNullOrEmpty(sysUser.getPassword())) throw new BaseException("密码不能为空");
//        if (!StringUtil.checkPasswordFormat(sysUser.getPassword())) throw new BaseException("密码格式不正确：长度为6-20 位，至少包含字母、数字以及标点符号其中两种（除空格）");
        if (Strings.isNullOrEmpty(shopBasic.getEmail()) || Strings.isNullOrEmpty(shopBasic.getEnterpriseName()) ||
                Strings.isNullOrEmpty(shopBasic.getContactPhone()) || Strings.isNullOrEmpty(shopBasic.getCountry())||Strings.isNullOrEmpty(shopBasic.getContactPerson())) {
            throw new BaseException("邮箱/企业名称/联系手机/国家/联系人不能为空");
        }
    }

    @Override
    public boolean sendEmailValidate(String email) {
        if (Strings.isNullOrEmpty(email)) throw new BaseException("sendValidate Email failed: Email不能为空");
        if(checkEmailExist(email)) throw new BaseException("邮箱已作为用户名存在");
        if(checkPhoneExist(email)) throw new BaseException("手机号已经存在");
        //发送邮箱
        emailSender.sendValidate(email);
        //将邮箱和key存放至redis
        String md5 = MD5Utils.getMd5(email);
        UserActivate emailActivate = new UserActivate();
        emailActivate.setEmailActivate(false);
        emailActivate.setEmail(email);
        emailActivate.setPhoneActivate(false);
        redisUtil.hset(RedisKey.REG_EMAIL_MAP, md5, emailActivate, 60 * 60 * 12);//24小时有效期
        System.out.println("邮箱发送成功");
        return true;
    }

    @Override
    public ResultVo activateEmail(String registryId) {
        if (Strings.isNullOrEmpty(registryId)) throw new BaseException("activate Email failed: registryId不能为空");
        //查询是否含有该注册ID，不是则是假ID或已过期
        UserActivate userActivate = (UserActivate) redisUtil.hget(RedisKey.REG_EMAIL_MAP, registryId);
        if (null == userActivate) return ResultVo.fail("该邮件已过期");
        //查询是否含有该邮箱名
        if (checkEmailExist(userActivate.getEmail())) return ResultVo.fail("邮箱已作为用户名存在");
        //验证成功，设置为激活
        userActivate.setEmailActivate(true);
        redisUtil.hset(RedisKey.REG_EMAIL_MAP, registryId, userActivate);
        //返回注册页面填充数据
        return ResultVo.ok(userActivate.getEmail());
    }

    /**
     * 发送短信或者验证短信验证码
     *
     * @param map
     * @return
     */
    @Override
    public ResultVo phoneValidate(Map<String, String> map) {
        String email = map.get("email");
        String phone = map.get("phone");
        String validateCode = map.get("validateCode");
        if (Strings.isNullOrEmpty(email) || Strings.isNullOrEmpty(phone)) return ResultVo.fail("参数异常");
        if (!Tools.isPhone(phone)) return ResultVo.fail("手机号不正确");
        //验证手机号是否存在
        if (sysUserService.checkPhoneExist(phone)) return ResultVo.fail("手机号已存在");
        //validate空则是发送验证码
        if (Strings.isNullOrEmpty(validateCode)) {
            //从缓存中取该用户的激活信息
            UserActivate userActivate = (UserActivate) redisUtil.hget(RedisKey.REG_EMAIL_MAP, MD5Utils.getMd5(email));
            if (null == userActivate || !userActivate.getEmailActivate()) throw new BaseException("异常操作");
            Integer RandomNum = Tools.get6BitRandomNum();
            ResultVo resultVo = SmsSendUtil.singleSender(phone, SmsSendUtil.QCLOUDSMS_REGISTRY_ID, RandomNum.toString(), "30");
            if(resultVo.getStatus() == 0) return resultVo;
            //存放验证码到redis
            if (!redisUtil.set(phone, RandomNum.toString())) throw new BaseException("redis异常");
            redisUtil.expire(phone, 60 * 30);//30分钟有效期
            return ResultVo.ok();
        } else {
            //获取之前存放的email
            UserActivate userActivate = (UserActivate) redisUtil.hget(RedisKey.REG_EMAIL_MAP, MD5Utils.getMd5(email));
            if (null == userActivate || !userActivate.getEmailActivate()) return ResultVo.fail("邮箱账号不存在或未激活");
            String redisValidateCode = (String) redisUtil.get(phone);
            //验证码不正确return false

            if (!redisValidateCode.equals(validateCode)) return ResultVo.fail("验证码不正确");
            userActivate.setPhone(phone);
            userActivate.setPhoneActivate(true);
            redisUtil.hset(RedisKey.REG_EMAIL_MAP, MD5Utils.getMd5(userActivate.getEmail()), userActivate);
            redisUtil.del(phone);
            return ResultVo.ok();
        }
    }
}
