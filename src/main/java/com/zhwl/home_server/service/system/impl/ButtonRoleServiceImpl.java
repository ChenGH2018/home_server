package com.zhwl.home_server.service.system.impl;

import com.zhwl.home_server.bean.system.ButtonRole;
import com.zhwl.home_server.mapper.system.button_role.ButtonRoleMapper;
import com.zhwl.home_server.service.system.ButtonRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("buttonRoleServiceImpl")
@Transactional
public class ButtonRoleServiceImpl implements ButtonRoleService {
    @Resource(name = "buttonRoleMapper")
    private ButtonRoleMapper buttonRoleMapper;


    @Override
    public List<String> getButtonIdByRoleId(String roleId) {
        return buttonRoleMapper.getButtonIdByRoleId(roleId);
    }

    @Override
    public Integer deleteByRoleId(String roleId) {
        return buttonRoleMapper.deleteByRoleId(roleId);
    }

    @Override
    public Integer saveByList(List<ButtonRole> list) {
        return buttonRoleMapper.saveByList(list);
    }
}
