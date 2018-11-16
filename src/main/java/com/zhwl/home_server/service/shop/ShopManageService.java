package com.zhwl.home_server.service.shop;

import com.zhwl.home_server.bean.shop.ShopRegistry;

import java.util.Map;

public interface ShopManageService {
    boolean checkShopEmail(String email);

     boolean registry(ShopRegistry shopRegistry);

    boolean sendEmailValidate(String email);

    void activateEmail(String registryId);

    boolean phoneValidate(Map<String, String> map);
}
