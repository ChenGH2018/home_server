package com.zhwl.home_server.controller.customerservice;

import com.zhwl.home_server.bean.Page;
import com.zhwl.home_server.bean.ResultVo;
import com.zhwl.home_server.bean.customerservice.CustomerService;
import com.zhwl.home_server.exception.BaseException;
import com.zhwl.home_server.service.customerservice.CustomerServiceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * Author: chenguihao
 * 说明：客服管理
 * 创建时间：2018-11-26
 */
@Api(tags = "客服管理")
@RestController
@RequestMapping("/customerService")
public class CustomerServiceController {

    @Autowired
    private CustomerServiceService customerServiceService;




    @ApiOperation(value = "修改", notes = "修改")
    @PutMapping("updateBySelective")
    public ResultVo updateBySelective(@RequestBody CustomerService customerService) {
        try {
            return ResultVo.ok(customerServiceService.updateBySelective(customerService));
        } catch (BaseException e) {
            e.printStackTrace();
            return ResultVo.fail(e.getCode(), e.getMessage());
        }
    }

    @ApiOperation(value = "查看", notes = "根据id查看")
    @GetMapping(value = "getCustomerServiceById/{id}")
    public ResultVo getCustomerServiceById(@PathVariable String id) {
        try {
            return ResultVo.ok(customerServiceService.selectById(id));
        } catch (BaseException e) {
            e.printStackTrace();
            return ResultVo.fail(e.getCode(), e.getMessage());
        }
    }

    @ApiOperation(value = "选择查询分页", notes = "选择查询分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "条数", paramType = "query")
    })
    @GetMapping("getCustomerServiceByPage")
    public ResultVo getCustomerServiceByPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size,
                                             CustomerService customerService) {
        Page pg = new Page();
        pg.setPage(page);
        pg.setSize(size);
        HashMap<String, Object> hp = new HashMap<>();
        hp.put("entity", customerService);
        pg.setPd(hp);
        try {
            pg.setData(customerServiceService.getCustomerServiceByPage(pg));
            return ResultVo.ok(pg);
        } catch (BaseException e) {
            e.printStackTrace();
            return ResultVo.fail(e.getCode(), e.getMessage());
        }
    }

    @ApiOperation(value = "选择查询所有", notes = "选择查询所有")
    @GetMapping("selectBySelective")
    public ResultVo selectBySelective(CustomerService customerService) {
        try {
            return ResultVo.ok(customerServiceService.selectBySelective(customerService));
        } catch (BaseException e) {
            e.printStackTrace();
            return ResultVo.fail(e.getCode(), e.getMessage());
        }
    }

    @ApiOperation(value = "物理删除一个或多个", notes = "物理删除一个或多个")
    @DeleteMapping("physicsDeleteByIds/{ids}")
    public ResultVo physicsDeleteByIds(@PathVariable String ids) {
        try {
            return ResultVo.ok(customerServiceService.physicsDeleteArray(ids.split(",")));
        } catch (BaseException e) {
            e.printStackTrace();
            return ResultVo.fail(e.getCode(), e.getMessage());
        }
    }

    @ApiOperation(value = "软删除一个或多个", notes = "软删除一个或多个")
    @DeleteMapping("softDeleteByIds/{ids}")
    public ResultVo softDeleteByIds(@PathVariable String ids) {
        try {
            return ResultVo.ok(customerServiceService.softDeleteArray(ids.split(",")));
        } catch (BaseException e) {
            e.printStackTrace();
            return ResultVo.fail(e.getCode(),e.getMessage());
        }
    }

    @ApiOperation(value = "添加商家客服", notes = "添加商家客服")
    @PostMapping("save")
    public ResultVo save(@RequestBody CustomerService customerService) {
        try {
            return ResultVo.ok(customerServiceService.saveCustomerService(customerService));
        } catch (BaseException e) {
            e.printStackTrace();
            return ResultVo.fail(e.getCode(), e.getMessage());
        }
    }
}
