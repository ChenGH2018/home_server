package com.zhwl.home_server.service.system;


import com.zhwl.home_server.bean.Page;
import com.zhwl.home_server.bean.system.Button;

import java.util.List;

/**
 * 说明： 按钮权限接口
 * 创建时间：2018-09-25
 */
public interface ButtonService {
    List<Button> selectAll();
    Page selectByPage(Page pg);
    Button selectById(String buttonId);
    List<Button> selectBySelective(Button button);

    Integer save(Button button);
    Integer updateBySelective(Button button);
    Integer deleteByIds(String[] ids);

    List<String> selectButtonNumberBySysUserId();

}

