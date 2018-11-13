package com.zhwl.home_server.bean.shopbasic;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "t_shop_basic")
public class ShopBasic {
    @ApiModelProperty(hidden = true)
    @Id
    private String id;
    private String email;//邮箱地址
    private String country;//国家
    private String enterpriseName;//企业名称
    private String enterpriseAddress;//企业所在地址
    private String contactPerson;//联系人
    private String contactPhone;//联系人手机号
    private Integer isFreeze;//是否冻结 0：未冻结、1：已冻结
    private Integer isLogout;//是否注销 0：未注销、1：已注销
    private Integer auditStatus;//审核状态 0：未审核、1：审核未通过、2：审核已通过
    private Date registerTime;//注册时间
    private String sysUserId;//系统用户外键

    @ApiModelProperty(hidden = true)
    private Boolean isQueryComplete;   //是否查询完善信息

}
