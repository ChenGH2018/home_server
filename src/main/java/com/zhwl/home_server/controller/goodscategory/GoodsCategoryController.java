package com.zhwl.home_server.controller.goodscategory;

import com.zhwl.home_server.bean.Page;
import com.zhwl.home_server.bean.ResultVo;
import com.zhwl.home_server.bean.goodscategory.GoodsCategory;
import com.zhwl.home_server.exception.BaseException;
import com.zhwl.home_server.service.goodscategory.GoodsCategoryService;
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
 * 说明：商品类别管理
 * 创建时间：2018-12-01
 */
@Api(tags = "商品类别管理")
@RestController
@RequestMapping("/goodsCategory")
public class GoodsCategoryController {

    @Autowired
    private GoodsCategoryService goodsCategoryService;


    @ApiOperation(value = "添加", notes = "添加")
    @PostMapping("save")
    public ResultVo save(@RequestBody GoodsCategory goodsCategory) {
        try {
            goodsCategory.setId(UuidUtil.get32UUID());
            return ResultVo.ok(goodsCategoryService.save(goodsCategory));
        } catch (BaseException e) {
            e.printStackTrace();
            return ResultVo.fail(e.getCode(),e.getMessage());
        }
    }


    @ApiOperation(value = "修改", notes = "修改")
    @PutMapping("updateBySelective")
    public ResultVo updateBySelective(@RequestBody GoodsCategory goodsCategory) {
        try {
            return ResultVo.ok(goodsCategoryService.updateBySelective(goodsCategory));
        } catch (BaseException e) {
            e.printStackTrace();
            return ResultVo.fail(e.getCode(),e.getMessage());
        }
    }

    @ApiOperation(value = "查看", notes = "根据id查看")
    @GetMapping(value = "getGoodsCategoryById/{id}")
    public ResultVo getGoodsCategoryById(@PathVariable String id) {
        try {
            return ResultVo.ok(goodsCategoryService.selectById(id));
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
    @GetMapping("getGoodsCategoryByPage")
    public ResultVo getGoodsCategoryByPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size,
                                          GoodsCategory goodsCategory) {
        Page pg = new Page();
        pg.setPage(page);
        pg.setSize(size);
        HashMap<String, Object> hp = new HashMap<>();
        hp.put("entity", goodsCategory);
        pg.setPd(hp);
        try {
            pg.setData(goodsCategoryService.getGoodsCategoryByPage(pg));
            return ResultVo.ok(pg);
        } catch (BaseException e) {
            e.printStackTrace();
            return ResultVo.fail(e.getCode(),e.getMessage());
        }
    }
    @ApiOperation(value = "选择查询所有", notes = "选择查询所有")
    @GetMapping("selectBySelective")
    public ResultVo selectBySelective(GoodsCategory goodsCategory) {
        try {
            return ResultVo.ok(goodsCategoryService.selectBySelective(goodsCategory));
        } catch (BaseException e) {
            e.printStackTrace();
            return ResultVo.fail(e.getCode(),e.getMessage());
        }
    }

    /*@ApiOperation(value = "物理删除一个或多个", notes = "物理删除一个或多个")
    @DeleteMapping("physicsDeleteByIds/{ids}")
    public ResultVo physicsDeleteByIds(@PathVariable String ids) {
        try {
            return ResultVo.ok(goodsCategoryService.physicsDeleteArray(ids.split(",")));
        } catch (BaseException e) {
            e.printStackTrace();
            return ResultVo.fail(e.getCode(),e.getMessage());
        }
    }*/
    @ApiOperation(value = "软删除一个或多个", notes = "软删除一个或多个")
    @DeleteMapping("softDeleteByIds/{ids}")
    public ResultVo softDeleteByIds(@PathVariable String ids) {
        try {
            return ResultVo.ok(goodsCategoryService.softDeleteArray(ids.split(",")));
        } catch (BaseException e) {
            e.printStackTrace();
            return ResultVo.fail(e.getCode(),e.getMessage());
        }
    }
}
