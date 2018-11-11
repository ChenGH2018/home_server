package com.zhwl.home_server.mapper.system.button;

import com.zhwl.home_server.bean.Page;
import com.zhwl.home_server.bean.system.Button;

import java.util.List;

/**
 * 说明： 按钮权限
 * 创建时间：2018-09-25
 */
public interface ButtonMapper {
    List<Button> selectBySelective(Button button);
    List<Button> selectByPage(Page pg);

    Integer save(Button button);

    Integer updateBySelective(Button button);

    Integer deleteByIds(String[] ids);

    boolean exitByButtonNumber(String buttonNumber);

    List<String> selectButtonNumberBySysUserId(String userId);
}
