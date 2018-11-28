package com.zhwl.home_server.controller.unitmanagement;

import com.zhwl.home_server.bean.Page;
import com.zhwl.home_server.bean.ResultVo;
import com.zhwl.home_server.bean.unitmanagement.UnitManagement;
import com.zhwl.home_server.exception.BaseException;
import com.zhwl.home_server.service.unitmanagement.UnitManagementService;
import com.zhwl.home_server.util.UuidUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * Author: chenguihao
 * 说明：价钱单位管理
 * 创建时间：2018-11-27
 */
@Api(tags = "价钱单位管理")
@RestController
@RequestMapping("/unitManagement")
public class UnitManagementController {

    @Autowired
    private UnitManagementService unitManagementService;


    @ApiOperation(value = "添加", notes = "添加")
    @PostMapping("save")
    public ResultVo save(@RequestBody UnitManagement unitManagement) {
        try {
            unitManagement.setId(UuidUtil.get32UUID());
            return ResultVo.ok(unitManagementService.save(unitManagement));
        } catch (BaseException e) {
            e.printStackTrace();
            return ResultVo.fail(e.getCode(),e.getMessage());
        }
    }


    @ApiOperation(value = "修改", notes = "修改")
    @PutMapping("updateBySelective")
    public ResultVo updateBySelective(@RequestBody UnitManagement unitManagement) {
        try {
            return ResultVo.ok(unitManagementService.updateBySelective(unitManagement));
        } catch (BaseException e) {
            e.printStackTrace();
            return ResultVo.fail(e.getCode(),e.getMessage());
        }
    }

    @ApiOperation(value = "查看", notes = "根据id查看")
    @GetMapping(value = "getUnitManagementById/{id}")
    public ResultVo getUnitManagementById(@PathVariable String id) {
        try {
            return ResultVo.ok(unitManagementService.selectById(id));
        } catch (BaseException e) {
            e.printStackTrace();
            return ResultVo.fail(e.getCode(),e.getMessage());
        }
    }

    @ApiOperation(value = "选择查询分页", notes = "选择查询分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "条数", paramType = "query")
    })
    @GetMapping("getUnitManagementByPage")
    public ResultVo getUnitManagementByPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size,
                                          UnitManagement unitManagement) {
        Page pg = new Page();
        pg.setPage(page);
        pg.setSize(size);
        HashMap<String, Object> hp = new HashMap<>();
        hp.put("entity", unitManagement);
        pg.setPd(hp);
        try {
            pg.setData(unitManagementService.getUnitManagementByPage(pg));
            return ResultVo.ok(pg);
        } catch (BaseException e) {
            e.printStackTrace();
            return ResultVo.fail(e.getCode(),e.getMessage());
        }
    }
    @ApiOperation(value = "选择查询所有", notes = "选择查询所有")
    @GetMapping("selectBySelective")
    public ResultVo selectBySelective(UnitManagement unitManagement) {
        try {
            return ResultVo.ok(unitManagementService.selectBySelective(unitManagement));
        } catch (BaseException e) {
            e.printStackTrace();
            return ResultVo.fail(e.getCode(),e.getMessage());
        }
    }

    @ApiOperation(value = "物理删除一个或多个", notes = "物理删除一个或多个")
    @DeleteMapping("physicsDeleteByIds/{ids}")
    public ResultVo physicsDeleteByIds(@PathVariable String ids) {
        try {
            return ResultVo.ok(unitManagementService.physicsDeleteArray(ids.split(",")));
        } catch (BaseException e) {
            e.printStackTrace();
            return ResultVo.fail(e.getCode(),e.getMessage());
        }
    }
}
