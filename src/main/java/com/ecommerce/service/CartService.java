package com.ecommerce.service;

import java.util.Map;

public interface CartService {

    public String baseURL = "/cart";
    public String appJson = "application/json";
    public Integer cartTotal = 3;
    public String createCart = "/createcart/{identification}/";
    public String deleteCart = "/deletecart/{identification}/";
    public String addProduct = "/addproduct/{identification}/";
    public String cartStatus = "/cartstatus/{identification}/";
    public String finishShop = "/finishshop/{identification}/";


    Long createCart(Long identification,Map<String,Object> json);
    void deleteCart(Long id);
    void addProduct(Long id, Map<String,Object> json);
    void removeProduct(Long id, Map<String, Object> json);
    Integer cartStatus(Long id);
    Long finishCart(Long id);

}
