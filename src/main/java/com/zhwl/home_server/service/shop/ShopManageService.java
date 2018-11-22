package com.zhwl.home_server.service.shop;

import com.zhwl.home_server.bean.ResultVo;
import com.zhwl.home_server.bean.shop.ShopRegistry;

import java.util.Map;

public interface ShopManageService {
    boolean checkEmailExist(String email);

    boolean checkPhoneExist(String email);

    boolean registry(ShopRegistry shopRegistry);

    boolean sendEmailValidate(String email);

    ResultVo activateEmail(String registryId);

    ResultVo phoneValidate(Map<String, String> map);
}
