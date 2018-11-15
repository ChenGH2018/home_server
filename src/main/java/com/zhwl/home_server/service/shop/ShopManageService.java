package com.zhwl.home_server.service.shop;

import com.zhwl.home_server.bean.shopcomplete.ShopComplete;

public interface ShopManageService {
    boolean checkShopEmail(String email);

     boolean registry(ShopComplete shopComplete);

    boolean sendEmailValidate(String email);
}
