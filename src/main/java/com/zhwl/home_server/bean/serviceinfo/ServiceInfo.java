package com.zhwl.home_server.bean.serviceinfo;

import com.zhwl.home_server.base.BaseBean;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Author by 陈桂豪 on 2018-11-22
 */

@Data
@Table(name = "t_service_info")
public class ServiceInfo extends BaseBean {

    @ApiModelProperty(hidden = true)
    @Id
    private String id;
    private String serviceName; //服务名
    private String serviceIcon; //服务图标
    private String serviceImg;  //服务图片URL
    private Double price;       //服务价格
    private String priceUnit;   //服务单元
    private String serviceContent; //服务说明
    private String remark;      //服务备注
    private Integer isOnline;   //是否上架 0:不上架 1:上架
    private String serviceCategoryId;
    private String shopBasicId;
    private Integer isDelete;   //是否删除 0：删除、1：删除
}
