package com.zhwl.home_server.bean.shop;

import com.zhwl.home_server.bean.system.SysUser;
import lombok.Data;

@Data
public class ShopRegistry {
    public SysUser sysUser;
    public ShopBasic shopBasic;
}
