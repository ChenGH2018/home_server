package com.zhwl.home_server.bean.optionalicon;

import javax.persistence.Id;
import javax.persistence.Table;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by admin on 2017/12/29.
 */
@Table(name = "t_optional_icon")
public class OptionalIcon {
    @Id
    private String id;
    private String name;//图标名称
    private Integer eabled;//是否启用
    private Date addTime;//添加时间

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

    public Integer getEabled() {
        return eabled;
    }

    public void setEabled(Integer eabled) {
        this.eabled = eabled;
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
