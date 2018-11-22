package com.zhwl.home_server.bean.shop;

import com.zhwl.home_server.bean.shopaudit.ShopAudit;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 */
@Data
@Table(name = "t_shop_complete")
public class ShopComplete {

    @Id
    private String id;
    private String shopBasicId;//商家基本信息外键
    private String shopName;//店铺名称
    private String companyHomepage;//公司主页
    private String companyType;//公司类型
    private String businessRegistrationNumber;//工商注册号
    private String unifiedSocialCreditCode;//统一社会信用代码
    private String registeredAddress;//注册地址
    private Double registeredCapital;//注册资本
    private Date establishmentDate;//成立日期
    private Date businessPeriodBegins;//营业期限开始日期
    private Date businessPeriodEnd;//营业期限结束日期
    private String fixedTelephone;//固定电话
    private String legalRepresentative;//法人代表
    private String businessLicenseUrl;//营业执照

    private boolean isQueryBasic;//是否关联查询基本信息
    private boolean isQueryAudit;//是否关联查询审核信息

    @ApiModelProperty(hidden = true)
    private ShopBasic shopBasic;    //商家基本信息
//    @ApiModelProperty(hidden = true)
    private ShopAudit shopAudit;    //商家审核信息

}
