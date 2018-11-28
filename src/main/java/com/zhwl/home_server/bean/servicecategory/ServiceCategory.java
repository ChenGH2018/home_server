package com.zhwl.home_server.bean.servicecategory;

import com.zhwl.home_server.base.BaseBean;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Author by 陈桂豪 on 2018-11-22
 */

@Data
@Table(name = "t_service_category")
public class ServiceCategory extends BaseBean{

    @ApiModelProperty(hidden = true)
    @Id
    private String id;
    private String  categoryName;   //类别名
    private String  categoryIcon;   //类别图标
    private String  categoryImg;   //类别图片
    private String  categoryImgKey;   //类别图片key
    private String  description;   //类别描述
    private Integer  isDelete;   //是否删除 0：不删除、1：删除
}
