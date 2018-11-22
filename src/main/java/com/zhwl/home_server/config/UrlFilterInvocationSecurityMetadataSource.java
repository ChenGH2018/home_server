package com.zhwl.home_server.config;

import com.zhwl.home_server.bean.system.Menu;
import com.zhwl.home_server.bean.system.Role;
import com.zhwl.home_server.service.system.MenuService;
import com.zhwl.home_server.util.Const;
import com.zhwl.home_server.util.SysUserUtil;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * Created by yihe on 2017/12/28.
 */
@Component
public class UrlFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    @Resource(name = "menuServiceImpl")
    private MenuService menuService;
    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        if(o != null ) return null;
        //获取请求地址
        String requestUrl = ((FilterInvocation) o).getRequestUrl();
        if ("/".equals(requestUrl) || requestUrl.matches(Const.NO_INTERCEPTOR_PATH) ||
                requestUrl.matches(Const.FRONT_DESK_PATH) || requestUrl.matches(Const.STATIC_FILE_PATH))
            return null;

        if (SysUserUtil.getCurrentUser().getId() == null)
            return SecurityConfig.createList("ROLE_LOGIN");

        List<Menu> allMenu = menuService.getAll();
        for (Menu menu : allMenu) {
            if (antPathMatcher.match(menu.getUrl(), requestUrl)) {
                List<Role> roles = menu.getRoles();
                int size = roles.size();
                if (size == 0) {
                    return SecurityConfig.createList("ROLE_LOGIN");
                }
                String[] values = new String[size];
                for (int i = 0; i < size; i++) {
                    values[i] = roles.get(i).getName();
                }
                return SecurityConfig.createList(values);
            }
        }
        return null;
        /*//没有匹配上的资源，都是登录访问
        return SecurityConfig.createList("ROLE_LOGIN");*/
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }
}
