package com.zhwl.home_server.bean.goodscategory;

import com.zhwl.home_server.base.BaseBean;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Author by chenguihao on 2018-12-01
 */

@Data
@Table(name = "t_goods_category")
public class GoodsCategory extends BaseBean{

    @ApiModelProperty(hidden = true)
    @Id
    private String id;
    private String  categoryName;   //类别名称
    private String  categoryDescription;   //类别描述
    private Integer  isDelete;   //是否删除 0：不删除、1：删除
}
