package com.zhwl.home_server.controller.system;

import com.zhwl.home_server.bean.Page;
import com.zhwl.home_server.bean.ResultVo;
import com.zhwl.home_server.bean.system.Role;
import com.zhwl.home_server.service.system.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Api(tags = "系统角色管理")
@RequestMapping("/system/role")
public class RoleController {

    @Resource(name = "roleServiceImpl")
    private RoleService roleService;

    @ApiOperation(value = "查看全部", notes = "查看全部")
    @GetMapping("/getAll")
    public ResultVo getAll(){
        try {
            return ResultVo.ok(roleService.selectAll());
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVo.fail(e.getMessage());
        }
    }

    @ApiOperation(value = "分页", notes = "分页")
    @GetMapping("/getByPage")
    public ResultVo getByPage(Page pg){
        try {
            return ResultVo.ok(roleService.selectByPage(pg));
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVo.fail(e.getMessage());
        }
    }

    @ApiOperation(value = "根据id查看", notes = "根据id查看")
    @GetMapping("/getById/{id}")
    public ResultVo getById(@PathVariable String id){
        try {
            return ResultVo.ok(roleService.selectById(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVo.fail(e.getMessage());
        }
    }

    @ApiOperation(value = "添加", notes = "添加")
    @PostMapping("/save")
    public ResultVo save(@RequestBody Role role) {
        try {
            return ResultVo.ok(roleService.save(role));
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVo.fail(e.getMessage());
        }
    }

    @ApiOperation(value = "修改", notes = "修改")
    @PutMapping("/update")
    public ResultVo update(@RequestBody Role role) {
        try {
            return ResultVo.ok(roleService.updateBySelective(role));
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVo.fail(e.getMessage());
        }
    }

    @ApiOperation(value = "删除", notes = "删除")
    @DeleteMapping("/delete/{ids}")
    public ResultVo delete(@PathVariable String ids) {
        try {
            return ResultVo.ok(roleService.deleteByIds(ids.split(",")));
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVo.fail(e.getMessage());
        }
    }

    @ApiOperation(value = "修改角色菜单权限", notes = "修改角色菜单权限")
    @PutMapping("/updateRoleMenu")
    public ResultVo updateRoleMenu(String roleId,String menuIds) {
        try {
            roleService.updateRoleMenu(roleId,menuIds.split(","));
            return ResultVo.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVo.fail(e.getMessage());
        }
    }

    @ApiOperation(value = "修改角色按钮权限", notes = "修改角色按钮权限")
    @PutMapping("/updateRoleButton")
    public ResultVo updateRoleButton(String roleId,String buttonIds) {
        try {
            roleService.updateRoleButton(roleId,buttonIds.split(","));
            return ResultVo.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVo.fail(e.getMessage());
        }
    }

}
