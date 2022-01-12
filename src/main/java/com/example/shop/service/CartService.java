package com.example.shop.service;

import com.example.shop.model.Cart;
import com.example.shop.model.Product;

import java.util.Set;

public interface CartService {
    Cart createCart(Integer id);
    boolean deleteCart(Cart cart);
    Cart getCartById(int id) throws MyException;
    Set<Cart> getAllCarts();
    Float addProduct(Product product);
    Float getSum();
    int getLastId();
}
