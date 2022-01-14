package com.example.shop.service;

import com.example.shop.model.Cart;

import java.util.Set;

public interface CartService {
    Cart createCart(Integer id);

    Integer createNextCart();

    boolean deleteCart(Integer id) throws MyException;

    Cart getCartById(int id) throws MyException;

    Set<Cart> getAllCarts();

    int getLastId();
}
