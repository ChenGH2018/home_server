package com.zhwl.home_server.bean.customerservice;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Author by chenguihao on 2018-11-26
 */

@Data
@Table(name = "t_customer_service")
public class CustomerService {

    @ApiModelProperty(hidden = true)
    @Id
    private String id;
    private String username;   //登录用户名
    private String password;   //登录密码
    private String name;   //姓名
    private String phone;   //联系手机
    private String email;   //电子邮箱
    private String address;   //联系地址
    private Integer isFreeze;   //是否冻结：0：不冻结、1：冻结
    private Date addTime;   //添加时间
    private Date updateTime;   //修改使用
    private String sysUserId;   //所属商家的UserID
}
