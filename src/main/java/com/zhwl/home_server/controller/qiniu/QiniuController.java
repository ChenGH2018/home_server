package com.zhwl.home_server.controller.qiniu;

import com.zhwl.home_server.bean.ResultVo;
import com.zhwl.home_server.bean.system.SysUser;
import com.zhwl.home_server.util.QiNiuUtil;
import com.zhwl.home_server.util.SysUserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Api(tags = {"七牛云存储接口"})
@RestController
@RequestMapping("qiniu")
public class QiniuController {

    @ApiOperation(value = "获取文件上传凭证",notes = "获取文件上传凭证")
    @GetMapping("getUploadKey")
    public ResultVo getUploadKey(){
        try {
            SysUser currentUser = SysUserUtil.getCurrentUser();
            String uploadKey = QiNiuUtil.getUploadKey();
            return ResultVo.ok(uploadKey);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVo.fail(e.getMessage());
        }
    }

    @ApiOperation(value = "文件上传（测试用）",notes = "文件上传（测试用）")
    @PostMapping("fileUpload")
    public ResultVo fileUpload(MultipartFile file){
        try {
            return ResultVo.ok(QiNiuUtil.uploadToServer(file,null));
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVo.fail(e.getMessage());
        }
    }
    @ApiOperation(value = "获取文件URL",notes = "获取文件URL")
    @ApiImplicitParam(value="文件key(上传文件后返回的key）",name = "fileKey",required = true,dataType = "string",paramType = "query")
    @GetMapping("getFileUrl")
    public ResultVo getFileUrl(String fileKey){
        try {
            return ResultVo.ok(QiNiuUtil.getDownloadUrl(fileKey) );
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVo.fail(e.getMessage());
        }
    }

    @ApiOperation(value = "删除一个或多个文件byKey",notes = "删除一个或多个文件byKey")
    @DeleteMapping("deleteFileByKeys")
    public ResultVo deleteFileByKeys(String fileKeys){
        try {
            QiNiuUtil.deleteUseKeys(fileKeys.split(","));
            return ResultVo.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVo.fail(e.getMessage());
        }
    }
}
