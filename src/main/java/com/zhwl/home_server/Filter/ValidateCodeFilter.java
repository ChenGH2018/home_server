package com.zhwl.home_server.Filter;

import com.alibaba.druid.util.StringUtils;
import com.zhwl.home_server.system.SysEnum;
import com.zhwl.home_server.util.Const;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class ValidateCodeFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws IOException, ServletException {
        if(StringUtils.equals("/login", httpServletRequest.getRequestURI())
                && StringUtils.equalsIgnoreCase(httpServletRequest.getMethod(), "post")) {
            // 1. 进行验证码的校验
            HttpSession session = httpServletRequest.getSession();
            String sessionCode = (String)session.getAttribute(Const.SESSION_SECURITY_CODE);		//获取session中的验证码
            String code = httpServletRequest.getParameter("validateCode");
            if((sessionCode == null || code == null) || !sessionCode.equalsIgnoreCase(code)){
                // 2. 校验不通过
                httpServletResponse.setContentType("application/json;charset=utf-8");
                PrintWriter out = httpServletResponse.getWriter();
                StringBuffer sb = new StringBuffer();
                sb.append("{\"status\":\"error\",\"msg\":\"");
                sb.append(SysEnum.VALIDATECODEERROR.getMsg());
                sb.append("\"}");
                out.write(sb.toString());
                out.flush();
                out.close();
                return;
            }
        }
        // 3. 校验通过，就放行
            filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
