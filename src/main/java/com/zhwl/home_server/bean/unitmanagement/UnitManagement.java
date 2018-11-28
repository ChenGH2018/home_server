
package com.zhwl.home_server.bean.unitmanagement;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Author by chenguihao on 2018-11-27
 */

@Data
@Table(name = "t_unit_management")
public class UnitManagement {

    @ApiModelProperty(hidden = true)
    @Id
    private String id;
    private String  unitName;   //单位名
    private String  description;   //单位描述
}
