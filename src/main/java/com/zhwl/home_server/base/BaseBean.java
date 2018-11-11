package com.zhwl.home_server.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
public class BaseBean {

    @ApiModelProperty(hidden = true)
    private String addUser;             //添加人
    @ApiModelProperty(hidden = true)
    private String updateUser;          //更新人
    @ApiModelProperty(hidden = true)
    private Date addTime;               //添加时间
    @ApiModelProperty(hidden = true)
    private Date updateTime;            //修改时间
    @ApiModelProperty(hidden = true)
    private String addTimeString;       //字符串格式的添加时间
    @ApiModelProperty(hidden = true)
    private String updateTimeString;    //字符串格式的更新时间

    public final void setAddTime(Date addTime) {
        this.addTime = addTime;
        addTimeString = (addTime != null ? new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(addTime) : "");
    }

    public final void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        updateTimeString = (updateTime != null ? new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(updateTime) : "");
    }
}
