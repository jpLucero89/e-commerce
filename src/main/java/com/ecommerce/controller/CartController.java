package com.ecommerce.controller;

import com.ecommerce.service.CartService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = CartService.baseURL)
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping(path = cartService.createCart,
            consumes = cartService.appJson)
    public void createCart(@PathVariable("identification") Long identification, @RequestBody String json) {
        try {
            Map<String,Object> result =
                    new ObjectMapper().readValue( json , HashMap.class);
          cartService.createCart(identification,result);
        } catch (Exception e) {
            throw new Error("No es posible crear el carro");
        }
    }

    @PostMapping(path = cartService.deleteCart)
    public void deleteCart(@PathVariable("identification") Long identification) {
        try {
          cartService.deleteCart(identification);
        } catch (Exception e) {
            throw new Error("No es posible borrar el carro");
        }
    }

    @PostMapping(path = cartService.addProduct,
            consumes = cartService.appJson)
    public void addProduct(@PathVariable("identification") Long identification, @RequestBody String json) {
        try {
            Map<String,Object> result =
                    new ObjectMapper().readValue( json , HashMap.class);
            cartService.addProduct(identification,result);
        } catch (Exception e) {
            throw new Error("No es posible agregar el producto al carro");
        }
    }

    @GetMapping(path = cartService.cartStatus,
            produces = cartService.appJson)
    public Integer cartStatus(@PathVariable("identification") Long identification) {
        try {
            return cartService.cartStatus(identification);
        } catch (Exception e) {
            throw new Error("No es posible encontrar el estado del carro solicitado");
        }
    }

    @PostMapping(path = cartService.finishShop,
            produces = cartService.appJson)
    public Long finishShop(@PathVariable("identification") Long identification) {
        try {
            return cartService.finishCart(identification);
        } catch (Exception e) {
            throw new Error("No es posible finalizar el carrito");
        }
    }

}
