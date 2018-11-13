package com.zhwl.home_server.service.system.impl;

import com.zhwl.home_server.bean.system.SysUserRole;
import com.zhwl.home_server.mapper.system.sysuser_role.SysUserRoleMapper;
import com.zhwl.home_server.service.system.SysUserRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("sysUserRoleServiceImpl")
@Transactional
public class SysUserRoleServiceImpl implements SysUserRoleService {
    @Resource(name = "sysUserRoleMapper")
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public Integer saveByList(List<SysUserRole> list) {
        return sysUserRoleMapper.saveByList(list);
    }

    @Override
    public Integer deleteBySysUserId(String sysUserId) {
        return sysUserRoleMapper.deleteBySysUserId(sysUserId);
    }
}