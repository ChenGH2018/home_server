package com.zhwl.home_server.controller.system;


import com.zhwl.home_server.bean.ResultVo;
import com.zhwl.home_server.bean.system.Menu;
import com.zhwl.home_server.service.system.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Api(tags = "系统菜单管理")
@RequestMapping("/system/menu")
public class MenuController {

    @Resource(name = "menuServiceImpl")
    private MenuService menuService;

    @ApiOperation(value = "查看菜单", notes = "查看菜单")
    @GetMapping("/getByParentId/{parentId}")
    public ResultVo getByParentId(@PathVariable String parentId){
        try {
            return ResultVo.ok(menuService.selectByParentId(parentId));
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVo.fail(e.getMessage());
        }
    }

    @ApiOperation(value = "根据角色Id获得菜单树", notes = "根据角色Id获得菜单树")
    @GetMapping("/tree/{roleId}")
    public ResultVo menuTree(@PathVariable String roleId) {
        try {
            return ResultVo.ok(menuService.menuTree(roleId));
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVo.fail(e.getMessage());
        }
    }
    @ApiOperation(value = "获得菜单树", notes = "获得菜单树")
    @GetMapping("/getMenuTree")
    public ResultVo getMenuTree() {
        try {
            return ResultVo.ok(menuService.getMenuTree());
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVo.fail(e.getMessage());
        }
    }

    @ApiOperation(value = "获得菜单下的按钮", notes = "获得菜单下的按钮")
    @GetMapping("/getMenuButtons")
    public ResultVo getMenuButtons() {
        try {
            return ResultVo.ok(menuService.getMenuButtons());
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVo.fail(e.getMessage());
        }
    }

    @ApiOperation(value = "根据角色Id获取按钮Id", notes = "根据角色Id获取按钮Id")
    @GetMapping("/getButtonIdByRoleId/{roleId}")
    public ResultVo getButtonIdByRoleId(@PathVariable String roleId) {
        try {
            return ResultVo.ok(menuService.getButtonIdByRoleId(roleId));
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVo.fail(e.getMessage());
        }
    }

    @ApiOperation(value = "根据id查看", notes = "根据id查看")
    @GetMapping("/getById/{id}")
    public ResultVo getById(@PathVariable String id){
        try {
            return ResultVo.ok(menuService.selectById(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVo.fail(e.getMessage());
        }
    }

    @ApiOperation(value = "添加", notes = "添加")
    @PostMapping("/save")
    public ResultVo save(@RequestBody Menu menu) {
        try {
            return ResultVo.ok(menuService.save(menu));
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVo.fail(e.getMessage());
        }
    }

    @ApiOperation(value = "修改", notes = "修改")
    @PutMapping("/update")
    public ResultVo update(@RequestBody Menu menu) {
        try {
            return ResultVo.ok(menuService.updateBySelective(menu));
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVo.fail(e.getMessage());
        }
    }

    @ApiOperation(value = "删除", notes = "删除")
    @DeleteMapping("/delete/{ids}")
    public ResultVo delete(@PathVariable String ids) {
        try {
            return ResultVo.ok(menuService.deleteByIds(ids.split(",")));
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVo.fail(e.getMessage());
        }
    }


}
