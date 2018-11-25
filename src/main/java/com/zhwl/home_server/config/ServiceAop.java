package com.zhwl.home_server.config;

import com.zhwl.home_server.base.BaseBean;
import com.zhwl.home_server.util.SysUserUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.Collection;
import java.util.Date;

@Aspect
@Configuration
@Order(1)
public class ServiceAop {

    //保存的切入点
    @Pointcut("execution(* com.zhwl.home_server.service..*.save*(..))")
    public void save() {
    }

    //修改的切入点
    @Pointcut("execution(* com.zhwl.home_server.service..*.update*(..))")
    public void update() {
    }

    @Before("save()")
    public void beforeSave(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if (args[0] instanceof BaseBean) {
            initAdd((BaseBean) args[0]);
            initUpdate((BaseBean) args[0]);
        } else if (args[0] instanceof Collection) {
            Collection collection = (Collection) args[0];
            if (collection.isEmpty()) return;
            collection.forEach(entity -> {
                if (entity instanceof BaseBean) {
                    initAdd(((BaseBean) entity));
                    initUpdate(((BaseBean) entity));
                }
            });
        }
    }

    @Before("update()")
    public void beforeUpdate(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if (args[0] instanceof BaseBean) {
            initUpdate((BaseBean) args[0]);
        } else if (args[0] instanceof Collection) {
            Collection collection = (Collection) args[0];
            if (collection.isEmpty()) return;
            collection.forEach(entity -> {
                if (entity instanceof BaseBean) {
                    initUpdate(((BaseBean) entity));
                }
            });
        }
    }

    private void initAdd(BaseBean baseBean) {
        baseBean.setAddTime(new Date());
        baseBean.setAddUser(SysUserUtil.getCurrentUser().getUsername());
    }

    private void initUpdate(BaseBean baseBean) {
        baseBean.setUpdateTime(new Date());
        baseBean.setUpdateUser(SysUserUtil.getCurrentUser().getUsername());
    }
}
