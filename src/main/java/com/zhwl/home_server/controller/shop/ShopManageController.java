package com.zhwl.home_server.controller.shop;

import com.zhwl.home_server.bean.ResultVo;
import com.zhwl.home_server.bean.shopcomplete.ShopComplete;
import com.zhwl.home_server.service.shop.ShopManageService;
import com.zhwl.home_server.util.UuidUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 说明：商家管理
 * 创建时间：2018-11-15
 */
@Api(tags = "商家管理")
@RequestMapping("/shop")
public class ShopManageController {

    @Autowired
    private ShopManageService shopManageService;


    @ApiOperation(value = "注册", notes = "注册")
    @PostMapping("registry")
    public ResultVo registry(@RequestBody ShopComplete shopComplete) {
        try {
            shopComplete.setId(UuidUtil.get32UUID());
            shopManageService.registry(shopComplete);
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
            if(shopManageService.checkShopEmail(email))//存在返回错误
                return ResultVo.fail("该邮箱账号作为用户名已经存在");
            return ResultVo.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVo.fail(e.getMessage());
        }
    }

    @ApiOperation(value = "发送邮箱验证", notes = "发送邮箱验证")
    @PostMapping("/sendEmailValidate")
    public ResultVo sendEmailValidate(String email) {
        try {
            if(shopManageService.sendEmailValidate(email))//true发送成功
                return ResultVo.ok();
            return ResultVo.fail("该邮箱账号作为用户名已经存在");

        } catch (Exception e) {
            e.printStackTrace();
            return ResultVo.fail(e.getMessage());
        }
    }

}
