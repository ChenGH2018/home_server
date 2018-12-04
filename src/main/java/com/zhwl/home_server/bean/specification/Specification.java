package com.zhwl.home_server.bean.specification;

import lombok.Data;


@Data
public class Specification {//规格实体类

    public String goodsSubCode; //商品子编号
    public Double price;    //价格
    public String quantity; //数量
    public Property[] properties;  //属性信息

    @Data
    public static class Property {
        public String propertyName; //属性名
        public String propertyValue;    //属性值
    }
}