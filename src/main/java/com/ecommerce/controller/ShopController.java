package com.ecommerce.controller;

import com.ecommerce.service.CartService;
import com.ecommerce.service.ShopService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = ShopService.baseURL)
public class ShopController {

    @Autowired
    private ShopService shopService;

    @GetMapping(path = shopService.shopDetail,
            consumes = shopService.appJson,
            produces = shopService.appJson)
    public void shopDetail(@PathVariable("identification") Long identification, @RequestBody String json) {
        try {
            Map<String,Object> result =
                    new ObjectMapper().readValue( json , HashMap.class);
            shopService.getShopDetail(identification,result);
        } catch (Exception e) {
            throw new Error("No es posible encontrar las compras solicitadas");
        }

    }
}
