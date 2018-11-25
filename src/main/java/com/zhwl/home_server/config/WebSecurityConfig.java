package com.zhwl.home_server.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhwl.home_server.Filter.ValidateCodeFilter;
import com.zhwl.home_server.service.system.SysUserService;
import com.zhwl.home_server.util.SysUserUtil;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;
import java.io.PrintWriter;

/**
 * Created by yihe on 2017/12/28.
 */
@Setter
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ConfigurationProperties(prefix = "zhwl")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource(name = "sysUserServiceImpl")
    private SysUserService sysUserService;

    private boolean developmentMode;

    @Resource
    private UrlFilterInvocationSecurityMetadataSource urlFilterInvocationSecurityMetadataSource;
    @Resource
    private UrlAccessDecisionManager urlAccessDecisionManager;
    @Resource
    private ValidateCodeFilter validateCodeFilter;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(sysUserService).passwordEncoder(new BCryptPasswordEncoder()); //添加自定义的userDetailsService认证
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        if (developmentMode)
            web.ignoring().antMatchers("/**");
        else
            web.ignoring().antMatchers("/index.html", "/static/**", /*"/login_p",*/
                    "/v2/api-docs", "/configuration/ui", "/swagger-resources/**", "/configuration/security",
                    "/swagger-ui.html", "/webjars/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        o.setSecurityMetadataSource(urlFilterInvocationSecurityMetadataSource);
                        o.setAccessDecisionManager(urlAccessDecisionManager);
                        return o;
                    }
                }).anyRequest().authenticated()
                .and().addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class).
                formLogin().loginPage("/login").loginProcessingUrl("/login").usernameParameter("username").passwordParameter("password").permitAll().
                failureHandler((httpServletRequest, httpServletResponse, e) -> {
                    httpServletResponse.setContentType("application/json;charset=utf-8");
                    PrintWriter out = httpServletResponse.getWriter();
                    StringBuffer sb = new StringBuffer();
                    sb.append("{\"status\":\"error\",\"msg\":\"");
                    if (e instanceof UsernameNotFoundException || e instanceof BadCredentialsException) {
                        sb.append("用户名或密码输入错误，登录失败!");
                    } else if (e instanceof DisabledException) {
                        sb.append("账户被禁用，登录失败，请联系管理员!");
                    } else {
                        sb.append("登录失败!");
                    }
                    sb.append("\"}");
                    out.write(sb.toString());
                    out.flush();
                    out.close();
                }).successHandler((httpServletRequest, httpServletResponse, authentication) -> {
            httpServletResponse.setContentType("application/json;charset=utf-8");
            PrintWriter out = httpServletResponse.getWriter();
            ObjectMapper objectMapper = new ObjectMapper();
            String s = "{\"status\":\"success\",\"msg\":" + objectMapper.writeValueAsString(SysUserUtil.getCurrentUser()) + "}";
            out.write(s);
            out.flush();
            out.close();
        }).and().logout().permitAll().and().csrf().disable();
    }

}