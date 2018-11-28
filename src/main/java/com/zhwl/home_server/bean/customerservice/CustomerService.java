package com.zhwl.home_server.bean.customerservice;

import com.zhwl.home_server.base.BaseBean;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Author by chenguihao on 2018-11-26
 */

@Data
@Table(name = "t_customer_service")
public class CustomerService extends BaseBean{

    @ApiModelProperty(hidden = true)
    @Id
    private String id;
    private String username;   //登录用户名
    private String password;   //登录密码
    private String fullName;   //姓名
    private String phone;   //联系手机
    private String email;   //电子邮箱
    private String address;   //联系地址
    private Integer isFreeze;   //是否冻结：0：不冻结、1：冻结
    private Integer isDelete;   //是否删除  0：不删除、1：删除
    private String sysUserId;   //系统用户ID
    private String shopBasicId; //所属商家ID
}
