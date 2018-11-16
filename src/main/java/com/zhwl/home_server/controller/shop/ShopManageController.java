package com.zhwl.home_server.controller.shop;

import com.zhwl.home_server.bean.ResultVo;
import com.zhwl.home_server.bean.shop.ShopRegistry;
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
    public ResultVo registry(@RequestBody ShopRegistry shopRegistry) {
        try {
            shopManageService.registry(shopRegistry);
            return ResultVo.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVo.fail(e.getMessage());
        }
    }

    @ApiOperation(value = "检查邮箱是否存在", notes = "true:存在 false：不存在")
    @PostMapping("/checkShopEmail")
    @ResponseBody
    public ResultVo checkShopEmail(String email) {
        try {
            if (shopManageService.checkShopEmail(email))//存在返回错误
                return ResultVo.fail("该邮箱账号作为用户名已经存在");
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
    @GetMapping("/activateEmail")//HashMap<String,String> map request
    public ResultVo activateEmail(String registryId) {
        try {
            shopManageService.activateEmail(registryId);//true发送成功
            return ResultVo.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVo.fail(e.getMessage());
        }
    }

    @ApiOperation(value = "手机发送短信验证", notes = "手机发送短信验证")
    @PostMapping("/phoneValidate")//HashMap<String,String> map request
    public ResultVo phoneValidate(@RequestBody Map<String,String> map) {
        try {
            if(shopManageService.phoneValidate(map)){//true发送成功
                return ResultVo.ok("短信发送成功");
            }
            return ResultVo.fail("短信发送失败");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVo.fail(e.getMessage());
        }
    }
}
