package com.zhwl.home_server.bean.shopaudit;

import com.zhwl.home_server.bean.shop.ShopBasic;
import com.zhwl.home_server.bean.shop.ShopComplete;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 */
@Data
@Table(name = "t_shop_audit")
public class ShopAudit {
    @Id
    private String id;
    private String shopCompleteId;//申请商家ID
    private String applicationPerson;//申请人
    private Date applicationTime;//申请时间
    private String applicationDescription;//申请说明
    private Integer auditStatus;//审核状态  0:未审批、1：已审批
    private String auditOpinion;//审核意见
    private Date auditTime;//审核时间
    private Integer auditResult;//审核结果  1：已通过、2：未通过

    private Boolean isQueryShop;//是否查询关联商家
    private Boolean isQueryShopBasic;   //是否查询商家基本信息
    private Boolean isQueryShopComplete;    //是否查询商家完善信息

    @ApiModelProperty(hidden = true)
    private ShopBasic shopBasic;//商家基本信息，包含商家完善信息
    @ApiModelProperty(hidden = true)
    private ShopComplete shopComplete;//商家，包含商家完善信息
}
