package com.zhwl.home_server.controller.customerservice;

import com.zhwl.home_server.bean.Page;
import com.zhwl.home_server.bean.ResultVo;
import com.zhwl.home_server.bean.customerservice.CustomerService;
import com.zhwl.home_server.exception.BaseException;
import com.zhwl.home_server.service.customerservice.CustomerServiceService;
import com.zhwl.home_server.util.UuidUtil;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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


    @ApiOperation(value = "添加", notes = "添加")
    @PostMapping("save")
    public ResultVo save(@RequestBody CustomerService customerService) {
        try {
            customerService.setId(UuidUtil.get32UUID());
            return ResultVo.ok(customerServiceService.save(customerService));
        } catch (BaseException e) {
            e.printStackTrace();
            return ResultVo.fail(e.getCode(), e.getMessage());
        }
    }


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

    @ApiOperation(value = "添加商家客服", notes = "传3个属性：username/password/customerService、customerService为实体类对象")
    @PostMapping("addCustomerService")
    public ResultVo addCustomerService(@ApiParam(name = "map",value = "传3个属性：username/password/customerService、customerService为实体类对象",type = "Map") Map<String,Object>map) {
        try {
            return ResultVo.ok(customerServiceService.addCustomerService(map));
        } catch (BaseException e) {
            e.printStackTrace();
            return ResultVo.fail(e.getCode(), e.getMessage());
        }
    }
}
