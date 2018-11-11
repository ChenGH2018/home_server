package com.zhwl.home_server.service.optionalicon.impl;

import com.zhwl.home_server.bean.optionalicon.OptionalIcon;
import com.zhwl.home_server.mapper.optionalicon.OptionalIconMapper;
import com.zhwl.home_server.service.optionalicon.OptionalIconService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("optionalIconServiceImpl")
public class OptionalIconServiceImpl implements OptionalIconService {

    @Resource(name = "optionalIconMapper")
    private OptionalIconMapper optionalIconMapper;

    @Override
    public List<OptionalIcon> selectAll() {
        return optionalIconMapper.selectBySelective(null);
    }
}
