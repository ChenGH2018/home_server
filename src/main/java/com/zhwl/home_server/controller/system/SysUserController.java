package com.zhwl.home_server.controller.system;

import com.zhwl.home_server.bean.Page;
import com.zhwl.home_server.bean.ResultVo;
import com.zhwl.home_server.bean.system.SysUser;
import com.zhwl.home_server.service.system.SysUserService;
import com.zhwl.home_server.util.UuidUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Api(tags = "系统用户管理")
@RequestMapping("/system/sysUser")
public class SysUserController {

    @Resource(name = "sysUserServiceImpl")
    private SysUserService sysUserService;

    @ApiOperation(value = "查看全部", notes = "查看全部")
    @GetMapping("/getAll")
    public ResultVo getAll(){
        try {
            return ResultVo.ok(sysUserService.selectAll());
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVo.fail(e.getMessage());
        }
    }

    @ApiOperation(value = "分页", notes = "分页")
    @GetMapping("/getByPage")
    public ResultVo getByPage(Page pg){
        try {
            return ResultVo.ok(sysUserService.selectByPage(pg));
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVo.fail(e.getMessage());
        }
    }

    @ApiOperation(value = "根据id查看", notes = "根据id查看")
    @GetMapping("/getById/{id}")
    public ResultVo getById(@PathVariable String id){
        try {
            return ResultVo.ok(sysUserService.selectById(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVo.fail(e.getMessage());
        }
    }

    @ApiOperation(value = "添加", notes = "添加")
    @PostMapping("/save")
    public ResultVo save(@RequestBody SysUser sysUser) {
        try {
            sysUser.setId(UuidUtil.get32UUID());
            return ResultVo.ok(sysUserService.save(sysUser));
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVo.fail(e.getMessage());
        }
    }

    @ApiOperation(value = "修改", notes = "修改")
    @PutMapping("/update")
    public ResultVo update(@RequestBody SysUser sysUser) {
        try {
            return ResultVo.ok(sysUserService.updateBySelective(sysUser));
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVo.fail(e.getMessage());
        }
    }

    @ApiOperation(value = "删除", notes = "删除")
    @DeleteMapping("/delete/{ids}")
    public ResultVo delete(@PathVariable String ids) {
        try {
            return ResultVo.ok(sysUserService.deleteByIds(ids.split(",")));
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVo.fail(e.getMessage());
        }
    }

    @ApiOperation(value = "修改用户角色", notes = "修改用户角色")
    @PutMapping("/updateRoles")
    public ResultVo updateRoles(String sysUserId,String roleIds) {
        try {
            sysUserService.updateRoles(sysUserId,roleIds.split(","));
            return ResultVo.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVo.fail(e.getMessage());
        }
    }

}
