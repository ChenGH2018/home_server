package com.zhwl.home_server.bean.categoryspecification;

import com.zhwl.home_server.base.BaseBean;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Author by chenguihao on 2018-12-01
 */

@Data
@Table(name = "t_category_specification")
public class CategorySpecification extends BaseBean {

    @ApiModelProperty(hidden = true)
    @Id
    private String id;
    private String categoryName;   //规格名称
    private String[] categoryValues;   //规格属性值
    private Integer isCustomize;   //是否可自定义 0：否、1：是
    private Integer isRequire;   //是否必填 0：否、1：是
    private String goodsCategoryId;   //关联商品类别表外键
}
