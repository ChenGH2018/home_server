package com.zhwl.home_server.service.system.impl;

import com.zhwl.home_server.bean.Page;
import com.zhwl.home_server.bean.system.Button;
import com.zhwl.home_server.exception.BaseException;
import com.zhwl.home_server.mapper.system.button.ButtonMapper;
import com.zhwl.home_server.service.system.ButtonService;
import com.zhwl.home_server.system.SysEnum;
import com.zhwl.home_server.util.SysUserUtil;
import com.zhwl.home_server.util.UuidUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * 说明： 按钮权限
 * 创建时间：2018-09-25
 */
@Service("buttonServiceImpl")
@Transactional
public class ButtonServiceImpl implements ButtonService {

    @Resource(name = "buttonMapper")
    private ButtonMapper buttonMapper;

    @Override
    public List<Button> selectAll() {
        return buttonMapper.selectBySelective(null);
    }
    @Override
    public Page selectByPage(Page pg) {
        pg.setData(buttonMapper.selectByPage(pg));
        return pg;
    }
    @Override
    public Button selectById(String id) {
        Button button = new Button();
        button.setId(id);
        List<Button> buttons = buttonMapper.selectBySelective(button);
        return buttons != null && buttons.size() == 1 ? buttons.get(0) : null;
    }
    @Override
    public List<Button> selectBySelective(Button button) {
        return buttonMapper.selectBySelective(button);
    }


    @Override
    public List<String> selectButtonNumberBySysUserId() {
        String userId = SysUserUtil.getCurrentUser().getId();
        if (userId == null)
            throw new BaseException(0,"无权限访问");
        return buttonMapper.selectButtonNumberBySysUserId(userId);
    }

    @Override
    public Integer save(Button button) {
        checkButtonNumber(button.getButtonNumber());
        checkMenuId(button.getMenuId());
        button.setId(UuidUtil.get32UUID());
        button.setAddTime(new Date());
        return buttonMapper.save(button);
    }

    @Override
    public Integer updateBySelective(Button button) {
        String buttonNumber = button.getButtonNumber(); //按钮编号
        String menuId = button.getMenuId(); //所属菜单id
        Button b = this.selectById(button.getId());
        Optional.ofNullable(b).ifPresent(x->{
            if (buttonNumber != null && !x.getButtonNumber().equals(buttonNumber)){//当按钮编号改变时
                checkButtonNumber(buttonNumber);
            }
            checkMenuId(menuId);
        });
        return buttonMapper.updateBySelective(button);
    }

    private void checkButtonNumber(String buttonNumber) {
        if (StringUtils.isEmpty(buttonNumber))
            throw new BaseException(SysEnum.BUTTONNUMBERNULL);
        if (buttonMapper.exitByButtonNumber(buttonNumber))
            throw new BaseException(SysEnum.BUTTONNUMBEREXIST);
    }

    private void checkMenuId(String menuId) {
        if (StringUtils.isEmpty(menuId))
            throw new BaseException(0,"请选择按钮所属菜单");
    }

    @Override
    public Integer deleteByIds(String[] ids) {
        return buttonMapper.deleteByIds(ids);
    }
}

