package com.zhwl.home_server.controller.optionalicon;

import com.zhwl.home_server.bean.ResultVo;
import com.zhwl.home_server.service.optionalicon.OptionalIconService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Api(tags = "自定义图标表")
@RequestMapping("/optionalIcon")
public class OptionalIconController {

    @Resource(name = "optionalIconServiceImpl")
    private OptionalIconService optionalIconService;

    @ApiOperation(value = "查看全部", notes = "查看全部")
    @GetMapping("/getAll")
    public ResultVo getAll(){
        try {
            return ResultVo.ok(optionalIconService.selectAll());
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVo.fail(e.getMessage());
        }
    }
}
