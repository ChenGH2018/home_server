package com.zhwl.home_server.mapper.optionalicon;

import com.zhwl.home_server.bean.optionalicon.OptionalIcon;

import java.util.List;

public interface OptionalIconMapper {
    List<OptionalIcon> selectBySelective(OptionalIcon optionalIcon);
}
