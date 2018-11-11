package com.zhwl.home_server.controller.shopaudit;


import com.zhwl.home_server.bean.Page;
import com.zhwl.home_server.bean.ResultVo;
import com.zhwl.home_server.bean.shopaudit.ShopAudit;
import com.zhwl.home_server.service.shopaudit.ShopAuditService;
import com.zhwl.home_server.util.UuidUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * 说明：商家审核管理
 * 创建时间：2018-11-10
 */
@Api(tags = "商家审核管理")
@RestController
@RequestMapping("/shopAudit")
public class ShopAuditController {

    @Autowired
    private ShopAuditService shopAuditService;


    @ApiOperation(value = "添加", notes = "添加")
    @PostMapping("save")
    public ResultVo save(@RequestBody ShopAudit shopAudit) {
        try {
            shopAudit.setId(UuidUtil.get32UUID());
            return ResultVo.ok(shopAuditService.save(shopAudit));
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVo.fail(e.getMessage());
        }
    }

    @ApiOperation(value = "删除一个或多个", notes = "删除一个或多个")
    @DeleteMapping("deleteByIds/{ids}")
    public ResultVo deleteByIds(@PathVariable String ids) {
        try {
            return ResultVo.ok(shopAuditService.deleteArray(ids.split(",")));
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVo.fail(e.getMessage());
        }
    }


    @ApiOperation(value = "修改", notes = "修改")
    @PutMapping("updateBySelective")
    public ResultVo updateBySelective(@RequestBody ShopAudit shopAudit) {
        try {
            return ResultVo.ok(shopAuditService.updateBySelective(shopAudit));
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVo.fail(e.getMessage());
        }
    }

    @ApiOperation(value = "查看", notes = "根据id查看")
    @GetMapping(value = "getShopAuditById/{id}")
    public ResultVo getShopAuditById(@PathVariable String id) {
        try {
            return ResultVo.ok(shopAuditService.selectById(id));
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
    @GetMapping("getShopAuditByPage")
    public ResultVo getShopAuditByPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size,
                                       ShopAudit shopAudit) {
        Page pg = new Page();
        pg.setPage(page);
        pg.setSize(size);
        HashMap<String, Object> hp = new HashMap<>();
        hp.put("entity", shopAudit);
        pg.setPd(hp);
        try {
            pg.setData(shopAuditService.getShopAuditByPage(pg));
            return ResultVo.ok(pg);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVo.fail(e.getMessage());
        }
    }

}
