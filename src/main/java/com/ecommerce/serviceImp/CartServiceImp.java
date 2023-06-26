package com.ecommerce.serviceImp;

import com.ecommerce.entity.Cart;
import com.ecommerce.entity.Product;
import com.ecommerce.entity.Shops;
import com.ecommerce.repository.CartRepository;
import com.ecommerce.repository.ShopRepository;
import com.ecommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CartServiceImp implements CartService {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ShopRepository shopRepository;

    @Override
    public Long createCart(Long identification, Map<String,Object> json) {
        try {
            Cart cart = new Cart();
            cart.setCartId(identification);
            Boolean isSpecial = (Boolean) json.get("isSpecial");
            if(isSpecial != null){
                cart.setIsSpecial(isSpecial);
            }
            Cart cartSaved = cartRepository.save(cart);
            return cartSaved.getId();
        }catch (Exception e){
            throw new Error("No se pudo crear el carro");
        }
    }

    @Override
    public void deleteCart(Long id) {
        try{
            cartRepository.deleteById(id);
        }catch (Exception e){
            throw new Error("No se pudo borrar el carro");
        }
    }

    @Override
    public void addProduct(Long id, Map<String, Object> json) {
        try{
            Optional<Cart> cart = cartRepository.findById(id);
            Product product = (Product) json.get("product");
            Cart cartFind = null;
            List<Product>products = new ArrayList<>();
            if(cart.isPresent()){
                cartFind = cart.get();
            }
            products.add(product);
            cartFind.setProduct(products);
            cartRepository.save(cartFind);
        }catch (Exception e){
            throw new Error("No se pudo agregar el producto");
        }
    }

    @Override
    public void removeProduct(Long id, Map<String, Object> json) {
        try {
            Optional<Cart> cart = cartRepository.findById(id);
            Cart cartFind = null;
            if (cart.isPresent()) {
                cartFind = cart.get();
            }
            List<Product> products = cartFind.getProduct();
            Product product = (Product) json.get("product");
            products.remove(product);
            cartFind.setProduct(products);
            cartRepository.save(cartFind);
        }catch (Exception e){
            throw new Error("No se pudo remover el producto");
        }
    }

    @Override
    public Integer cartStatus(Long id) {
        try {
            Optional<Cart> cart = cartRepository.findById(id);
            Cart cartFind = null;
            if (cart.isPresent()) {
                cartFind = cart.get();
            }
            return cartFind.getProduct().size();
        }catch(Exception e){
            throw new Error("No se pudo remover el producto");
        }
    }

    @Override
    public Long finishCart(Long id) {
        try {
            Optional<Cart> cart = cartRepository.findById(id);
            Cart cartFind = null;
            if (cart.isPresent()) {
                cartFind = cart.get();
            }
            Shops shops = new Shops();
            List <Cart> carts = new ArrayList<>();
            Double total = finishCartTotal(cartFind);
            carts.add(cartFind);
            shops.setCarts(carts);
            shops.setTotalCart(total);
            shops = shopRepository.save(shops);
            return shops.getId();
        }catch (Exception e) {
            throw new Error("No se pudo finalizar el carrito");
        }
    }

    private Double finishCartTotal(Cart cartFind) {
        List<Product> products = cartFind.getProduct();
        Double total = 0.0;
        for (Product product : products) {
            if(product.getQuantity() >= 4){
                total += (product.getPrice() * product.getQuantity()) - 1;
            }else {
                total += product.getPrice() * product.getQuantity();
            }
        }
        if(products.size() >= cartTotal ){
            if (cartFind.getIsSpecial()){
                total -= 150.0;
            }else {
                total -= 100.0;
            }
        }
        return  total;
    }

}
