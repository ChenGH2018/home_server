package com.zhwl.home_server.util;

import com.zhwl.home_server.bean.system.SysUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SysUserUtil {
    public static SysUser getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication==null)
            return new SysUser();
        Object principal =authentication.getPrincipal();
        if(principal instanceof SysUser)
        {
            SysUser user = (SysUser)principal;
            user.setPassword(null);
            return  user;
        }else {
            return new SysUser();
        }
    }

    public static SysUser getCurrentRole() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication==null)
            return new SysUser();
        Object principal =authentication.getPrincipal();
        if(principal instanceof SysUser)
        {
            SysUser user = (SysUser)principal;
            user.setPassword(null);
            return  user;
        }else {
            return new SysUser();
        }
    }
}
