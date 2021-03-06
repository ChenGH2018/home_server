package com.zhwl.home_server.util;

import com.google.common.base.Strings;
import com.zhwl.home_server.bean.shop.ShopBasic;
import com.zhwl.home_server.bean.system.SysUser;
import com.zhwl.home_server.service.shop.ShopBasicService;
import com.zhwl.home_server.system.UserTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SysUserUtil {

    private static ShopBasicService shopBasicService;

    @Autowired
    public void setShopBasicService(ShopBasicService shopBasicService) {
        SysUserUtil.shopBasicService = shopBasicService;
    }

    public static SysUser getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null)
            return new SysUser();
        Object principal = authentication.getPrincipal();
        if (principal instanceof SysUser) {
            SysUser user = (SysUser) principal;
            user.setPassword(null);
            return user;
        } else {
            return new SysUser();
        }
    }

    public static SysUser getNonNullUser() {
        SysUser currentUser = getCurrentUser();
        if(Strings.isNullOrEmpty(currentUser.getUsername())) return null;
        return currentUser;
    }

    public static ShopBasic getCurrentShopBasic() {
        SysUser currentUser = getCurrentUser();
        if (currentUser.getUserType() == null || currentUser.getUserType() != UserTypeEnum.SHOPUSER.getType())
            return null;
        return currentUser.getShopBasic();
    }
    public static String getCurrentShopBasicId() {
        return null == getCurrentShopBasic()? null:getCurrentShopBasic().getId();
    }
}
