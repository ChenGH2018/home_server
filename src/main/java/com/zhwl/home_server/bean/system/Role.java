package com.zhwl.home_server.bean.system;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Id;
import javax.persistence.Table;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yihe on 2017/12/28.
 */
@Table(name = "role")
public class Role {
    @Id
    private String id;
    private String name;
    private String nameZh;
    @ApiModelProperty(hidden = true)
    private Date addTime;  //添加时间

    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameZh() {
        return nameZh;
    }

    public void setNameZh(String nameZh) {
        this.nameZh = nameZh;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getAddTimeToString() {
        return addTime == null ? "" : new SimpleDateFormat("yyyy-MM-dd").format(addTime);
    }
}
