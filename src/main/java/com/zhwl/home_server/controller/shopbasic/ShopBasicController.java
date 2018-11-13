package com.zhwl.home_server.controller.shopbasic;


import com.zhwl.home_server.bean.Page;
import com.zhwl.home_server.bean.ResultVo;
import com.zhwl.home_server.bean.shopbasic.ShopBasic;
import com.zhwl.home_server.service.shopbasic.ShopBasicService;
import com.zhwl.home_server.util.UuidUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * 说明：公司基本信息
 * 创建时间：2018-11-09
 */
@Api(tags = "商家基本信息")
@RestController
@RequestMapping("/shopBasic")
public class ShopBasicController {

    @Autowired
    private ShopBasicService shopBasicService;


    @ApiOperation(value = "保存", notes = "保存")
    @PostMapping("/save")
    public ResultVo save(@RequestBody ShopBasic shopBasic) {
        try {
            shopBasic.setId(UuidUtil.get32UUID());
            return ResultVo.ok(shopBasicService.save(shopBasic));
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVo.fail(e.getMessage());
        }
    }

    @ApiOperation(value = "修改", notes = "修改")
    @PutMapping("updateBySelective")
    public ResultVo updateBySelective(@RequestBody ShopBasic shopBasic) {
        try {
            return ResultVo.ok(shopBasicService.updateBySelective(shopBasic));
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVo.fail(e.getMessage());
        }
    }

    @ApiOperation(value = "通过ID查看", notes = "根据id查看")
    @GetMapping(value = "getShopBasicById/{id}")
    public ResultVo getShopBasicById(@PathVariable String id) {
        try {
            return ResultVo.ok(shopBasicService.selectById(id));
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
    @GetMapping("getShopBasicByPage")
    public ResultVo getShopBasicByPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size,
                                       ShopBasic shopBasic) {
        Page pg = new Page();
        pg.setPage(page);
        pg.setSize(size);
        HashMap<String, Object> hp = new HashMap<>();
        hp.put("entity",shopBasic);
        pg.setPd(hp);
        try {
            pg.setData(shopBasicService.getShopBasicByPage(pg));
            return ResultVo.ok(pg);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVo.fail(e.getMessage());
        }
    }

}
