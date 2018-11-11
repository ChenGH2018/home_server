package com.zhwl.home_server.controller.system;


import com.zhwl.home_server.bean.ResultVo;
import com.zhwl.home_server.bean.system.SysUser;
import com.zhwl.home_server.service.system.ButtonService;
import com.zhwl.home_server.service.system.MenuService;
import com.zhwl.home_server.service.system.SysUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 这是一个只要登录就能访问的Controller
 * 主要用来获取一些配置信息
 */
@RestController
@RequestMapping("/config")
public class ConfigController {

    @Resource(name = "menuServiceImpl")
    private MenuService menuService;
    @Resource(name = "buttonServiceImpl")
    private ButtonService buttonService;
    @Resource(name = "sysUserServiceImpl")
    private SysUserService sysUserService;

    @GetMapping("/sysMenu")
    public ResultVo sysMenu() {
        try {
            return ResultVo.ok(menuService.selectBySysUserId());
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVo.fail(e.getMessage());
        }
    }

    @GetMapping("/sysButton")
    public ResultVo sysButton() {
        try {
            return ResultVo.ok(buttonService.selectButtonNumberBySysUserId());
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVo.fail(e.getMessage());
        }
    }

    @PutMapping("/sysUser/update")
    public ResultVo updateSysUser(@RequestBody SysUser sysUser) {
        try {
            return ResultVo.ok(sysUserService.updateSysUser(sysUser));
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVo.fail(e.getMessage());
        }
    }

}
