package com.zhwl.home_server.bean.goodsinfo;

import com.zhwl.home_server.base.BaseBean;
import com.zhwl.home_server.bean.specification.Specification;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

/**
 * Author by chenguihao on 2018-12-03
 */

@Data
@Table(name = "t_goods_info")
public class GoodsInfo extends BaseBean {
    @ApiModelProperty(hidden = true)
    @Id
    private String id;
    private Integer goodsCode;   //商品编号
    private String goodsName;   //商品名称
    private String goodsExplain;   //商品说明
    private String[] imgKeys;   //商品图片keys
    private String[] imgUrls;   //商品图片的urls
    private Integer isAdded;   //是否上架 0：否 1：是
    private Integer isDelete;   //是否删除 0：否、1：是
    private List<Specification> goodsSpec;   //商品规格
    private String shopBasicId;   //外键关联商家表
    private String goodsCategoryId;   //外键关联商品类别表
}
