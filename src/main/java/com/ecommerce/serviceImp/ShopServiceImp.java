package com.ecommerce.serviceImp;

import com.ecommerce.repository.ShopRepository;
import com.ecommerce.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ShopServiceImp implements ShopService {

    @Autowired
    private ShopRepository shopRepository;

    @Override
    public void getShopDetail(Long identification, Map<String, Object> json) {

    }
}
