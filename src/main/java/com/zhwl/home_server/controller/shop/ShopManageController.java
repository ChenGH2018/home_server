package com.zhwl.home_server.controller.shop;

import com.zhwl.home_server.bean.ResultVo;
import com.zhwl.home_server.bean.shop.ShopRegistry;
import com.zhwl.home_server.exception.BaseException;
import com.zhwl.home_server.service.shop.ShopManageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 说明：商家管理
 * 创建时间：2018-11-15
 */
@Api(tags = "商家管理")
@Controller
@RequestMapping("/shopManage")
public class ShopManageController {

    @Autowired
    private ShopManageService shopManageService;


    @ApiOperation(value = "注册", notes = "注册")
    @PostMapping("registry")
    @ResponseBody
    public ResultVo registry(@RequestBody ShopRegistry shopRegistry) {
        try {
            if(shopManageService.registry(shopRegistry))
                return ResultVo.ok("注册成功");
            return ResultVo.ok("注册失败");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVo.fail(e.getMessage());
        }
    }

    @ApiOperation(value = "检查邮箱是否存在", notes = "true:存在 false：不存在")
    @PostMapping("/checkShopEmail")
    @ResponseBody
    public ResultVo checkShopEmail(@RequestBody Map<String,String> map) {
        try {
            if (shopManageService.checkEmailExist(map.get("email")))//存在返回错误
                return ResultVo.fail("该邮箱账号作为用户名已经存在");
            return ResultVo.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVo.fail(e.getMessage());
        }
    }

    @ApiOperation(value = "检查手机是否存在", notes = "传phone参数、true:存在 false：不存在")
    @PostMapping("/checkShopPhone")
    @ResponseBody
    public ResultVo checkShopPhone(@RequestBody Map<String,String> map) {
        try {
            if (shopManageService.checkPhoneExist(map.get("phone")))//存在返回错误
                return ResultVo.fail("手机号已经存在");
            return ResultVo.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVo.fail(e.getMessage());
        }
    }

    @ApiOperation(value = "发送邮箱验证", notes = "发送邮箱验证")
    @ResponseBody
    @PostMapping("/sendEmailValidate")//HttpServletRequest request
    public ResultVo sendEmailValidate(@RequestBody HashMap<String,String> map) {
        try {
            shopManageService.sendEmailValidate(map.get("email"));//true发送成功
            return ResultVo.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVo.fail(e.getMessage());
        }
    }

    @ApiOperation(value = "激活邮箱", notes = "激活邮箱")
    @GetMapping("/activateEmail")
    public String activateEmail(String registryId) {
        try {//true发送成功
            ResultVo resultVo = shopManageService.activateEmail(registryId);
            if(resultVo.getStatus() == 0)
                throw new BaseException(resultVo.getMsg());
            return "redirect:http://192.168.100.87:3333/account/businessRegister?email="+ resultVo.getData();
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @ApiOperation(value = "发送短信验证/验证码校验", notes = "传email、phone、validateCode/validateCode为空则发送短信，否则校验短信")
    @PostMapping("/phoneValidate")//HashMap<String,String> map request
    @ResponseBody
    public ResultVo phoneValidate(@RequestBody Map<String,String> map) {
        try {
            return shopManageService.phoneValidate(map);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVo.fail(e.getMessage());
        }
    }
}
