package com.zhwl.home_server.controller.categoryspecification;

import com.zhwl.home_server.bean.Page;
import com.zhwl.home_server.bean.ResultVo;
import com.zhwl.home_server.bean.categoryspecification.CategorySpecification;
import com.zhwl.home_server.exception.BaseException;
import com.zhwl.home_server.service.categoryspecification.CategorySpecificationService;
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
 * 说明：类别规格管理
 * 创建时间：2018-12-01
 */
@Api(tags = "类别规格管理")
@RestController
@RequestMapping("/categorySpecification")
public class CategorySpecificationController {

    @Autowired
    private CategorySpecificationService categorySpecificationService;


    @ApiOperation(value = "添加", notes = "添加")
    @PostMapping("save")
    public ResultVo save(@RequestBody CategorySpecification categorySpecification) {
        try {
            categorySpecification.setId(UuidUtil.get32UUID());
            return ResultVo.ok(categorySpecificationService.save(categorySpecification));
        } catch (BaseException e) {
            e.printStackTrace();
            return ResultVo.fail(e.getCode(),e.getMessage());
        }
    }


    @ApiOperation(value = "修改", notes = "修改")
    @PutMapping("updateBySelective")
    public ResultVo updateBySelective(@RequestBody CategorySpecification categorySpecification) {
        try {
            return ResultVo.ok(categorySpecificationService.updateBySelective(categorySpecification));
        } catch (BaseException e) {
            e.printStackTrace();
            return ResultVo.fail(e.getCode(),e.getMessage());
        }
    }

    @ApiOperation(value = "查看", notes = "根据id查看")
    @GetMapping(value = "getCategorySpecificationById/{id}")
    public ResultVo getCategorySpecificationById(@PathVariable String id) {
        try {
            return ResultVo.ok(categorySpecificationService.selectById(id));
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
    @GetMapping("getCategorySpecificationByPage")
    public ResultVo getCategorySpecificationByPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size,
                                          CategorySpecification categorySpecification) {
        Page pg = new Page();
        pg.setPage(page);
        pg.setSize(size);
        HashMap<String, Object> hp = new HashMap<>();
        hp.put("entity", categorySpecification);
        pg.setPd(hp);
        try {
            pg.setData(categorySpecificationService.getCategorySpecificationByPage(pg));
            return ResultVo.ok(pg);
        } catch (BaseException e) {
            e.printStackTrace();
            return ResultVo.fail(e.getCode(),e.getMessage());
        }
    }
    @ApiOperation(value = "选择查询所有", notes = "选择查询所有")
    @GetMapping("selectBySelective")
    public ResultVo selectBySelective(CategorySpecification categorySpecification) {
        try {
            return ResultVo.ok(categorySpecificationService.selectBySelective(categorySpecification));
        } catch (BaseException e) {
            e.printStackTrace();
            return ResultVo.fail(e.getCode(),e.getMessage());
        }
    }

    @ApiOperation(value = "物理删除一个或多个", notes = "物理删除一个或多个")
    @DeleteMapping("physicsDeleteByIds/{ids}")
    public ResultVo physicsDeleteByIds(@PathVariable String ids) {
        try {
            return ResultVo.ok(categorySpecificationService.physicsDeleteArray(ids.split(",")));
        } catch (BaseException e) {
            e.printStackTrace();
            return ResultVo.fail(e.getCode(),e.getMessage());
        }
    }
}
