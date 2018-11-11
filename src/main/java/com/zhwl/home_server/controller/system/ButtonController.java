package com.zhwl.home_server.controller.system;


import com.zhwl.home_server.bean.Page;
import com.zhwl.home_server.bean.ResultVo;
import com.zhwl.home_server.bean.system.Button;
import com.zhwl.home_server.service.system.ButtonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * 说明：按钮权限
 * 创建时间：2018-09-25
 */
@Api(tags = "按钮权限")
@RestController
@RequestMapping("/system/button")
public class ButtonController {

    @Resource(name = "buttonServiceImpl")
    private ButtonService buttonService;


    @ApiOperation(value = "添加", notes = "添加")
    @PostMapping("/save")
    public ResultVo save(@RequestBody Button button) {
        try {
            return ResultVo.ok(buttonService.save(button));
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVo.fail(e.getMessage());
        }
    }

    @ApiOperation(value = "删除", notes = "删除")
    @DeleteMapping("/deleteByIds/{ids}")
    public ResultVo deleteByIds(@PathVariable String ids) {
        try {
            return ResultVo.ok(buttonService.deleteByIds(ids.split(",")));
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVo.fail(e.getMessage());
        }
    }

    @ApiOperation(value = "修改", notes = "修改")
    @PutMapping("update")
    public ResultVo update(@RequestBody Button button) {
        try {
            return ResultVo.ok(buttonService.updateBySelective(button));
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVo.fail(e.getMessage());
        }
    }

    @ApiOperation(value = "查看", notes = "根据id查看")
    @GetMapping(value = "/getById/{id}")
    public ResultVo getById(@PathVariable String id) {
        try {
            return ResultVo.ok(buttonService.selectById(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVo.fail(e.getMessage());
        }
    }

    @ApiOperation(value = "分页查看", notes = "分页")
    @GetMapping("/getByPage")
    public ResultVo getByPage(Page pg, String menuName) {
        try {
            HashMap<String,String> map = new HashMap<String,String>();
            map.put("menuName",menuName);
            pg.setPd(map);
            return ResultVo.ok(buttonService.selectByPage(pg));
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVo.fail(e.getMessage());
        }
    }

}
