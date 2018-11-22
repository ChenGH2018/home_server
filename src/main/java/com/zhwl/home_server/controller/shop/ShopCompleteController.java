package com.zhwl.home_server.controller.shop;


import com.zhwl.home_server.bean.Page;
import com.zhwl.home_server.bean.ResultVo;
import com.zhwl.home_server.bean.shop.ShopComplete;
import com.zhwl.home_server.service.shop.ShopCompleteService;
import com.zhwl.home_server.util.UuidUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * 说明：商家完善信息
 * 创建时间：2018-11-10
 */
@Api(tags = "商家完善信息")
@RestController
@RequestMapping("/shop/shopComplete")
public class ShopCompleteController {

    @Autowired
    private ShopCompleteService shopCompleteService;


    @ApiOperation(value = "添加", notes = "添加")
    @PostMapping("save")
    public ResultVo save(@RequestBody ShopComplete shopComplete) {
        try {
            shopComplete.setId(UuidUtil.get32UUID());
            return ResultVo.ok(shopCompleteService.save(shopComplete));
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVo.fail(e.getMessage());
        }
    }


    @ApiOperation(value = "修改", notes = "修改")
    @PutMapping("updateBySelective")
    public ResultVo updateBySelective(@RequestBody ShopComplete shopComplete) {
        try {
            return ResultVo.ok(shopCompleteService.updateBySelective(shopComplete));
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVo.fail(e.getMessage());
        }
    }
    @ApiOperation(value = "删除一个或多个", notes = "删除一个或多个")
    @DeleteMapping("deleteByIds/{ids}")
    public ResultVo deleteByIds(@PathVariable String ids) {
        try {
            return ResultVo.ok(shopCompleteService.deleteArray(ids.split(",")));
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVo.fail(e.getMessage());
        }
    }

    @ApiOperation(value = "查看", notes = "根据id查看")
    @GetMapping(value = "/{id}")
    public ResultVo getShopCompleteById(@PathVariable String id) {
        try {
            return ResultVo.ok(shopCompleteService.selectById(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVo.fail(e.getMessage());
        }
    }

    @ApiOperation(value = "分页查看", notes = "分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "条数", paramType = "query")
    })
    @GetMapping("getShopCompleteByPage")
    public ResultVo getShopCompleteByPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size,
                                          ShopComplete shopComplete) {
        Page pg = new Page();
        pg.setPage(page);
        pg.setSize(size);
        HashMap<String, Object> hp = new HashMap<>();
        hp.put("entity", shopComplete);
        pg.setPd(hp);
        try {
            pg.setData(shopCompleteService.getShopCompleteByPage(pg));
            return ResultVo.ok(pg);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVo.fail(e.getMessage());
        }
    }

}
