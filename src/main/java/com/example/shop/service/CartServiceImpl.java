package com.example.shop.service;

import com.example.shop.model.Cart;
import com.example.shop.storage.CartStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

import static com.example.shop.storage.CartStorage.cartStorageSet;

@Service
public class CartServiceImpl implements CartService {

    private final CartStorage cartStorage;

    @Autowired
    public CartServiceImpl(CartStorage cartStorage) {
        this.cartStorage = cartStorage;
    }

    public Cart createCart(Integer Id) {
        Cart newCart = new Cart();
        newCart.setId(Id);
        cartStorageSet.add(newCart);
        return newCart;
    }

    @Override
    public Integer createNextCart() {
        Cart cart = createCart(getLastId()+1);
        return cart.getId();
    }

    @Override
    public boolean deleteCart(Integer id) throws MyException {
        Cart cart = getCartById(id);
        return cartStorageSet.remove(cart);
    }

    @Override
    public Cart getCartById(int id) throws MyException {
        try {
            return cartStorageSet.stream().filter(p -> p.getId().equals(id)).findFirst().get();
        } catch (Exception ex) {
            throw new MyException("Wrong idCart");
        }
    }

    @Override
    public Set<Cart> getAllCarts() {
        return cartStorageSet;
    }

    @Override
    public int getLastId() {
        int maxId = 0;
        for (Cart c : cartStorageSet) {
            if (maxId < c.getId()) {
                maxId = c.getId();
            }
        }
        return maxId;
    }
}
