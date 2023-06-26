package com.ecommerce.service;

import java.util.Map;

public interface ShopService {

    public String baseURL = "/shop";
    public String appJson = "application/json";
    public String shopDetail = "/shopdetail/{identification}/";

    void getShopDetail(Long identification, Map<String,Object> json);

}
