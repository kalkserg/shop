package com.example.shop.service;

import com.example.shop.model.Cart;
import com.example.shop.model.Cart;
import com.example.shop.model.Person;
import com.example.shop.storage.CartStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static com.example.shop.storage.CartStorage.cartStorageSet;
import static com.example.shop.storage.PersonStorage.personStorageSet;
import static org.junit.jupiter.api.Assertions.*;

class CartServiceImplTest {

    CartStorage cartStorage;
    CartServiceImpl cartServiceImpl = new CartServiceImpl(cartStorage);

    Cart cart1;
    Cart cart2;
    
    @BeforeEach
    void create() {
        cart1 = new Cart();
        cart2 = new Cart();
        cart1.setId(1);
        cart2.setId(2);
        cartServiceImpl.createNextCart();
        cartServiceImpl.createNextCart();
    }

    @Test
    void createCartTest() {
        Set<Cart> expected = cartStorageSet;
        Set<Cart> actual = new HashSet<>();
        actual.add(cart1);
        actual.add(cart2);
        assertEquals(expected, actual);
    }

    @Test
    void deleteCartTest() {
        try {
            cartServiceImpl.deleteCart(1);
        } catch (MyException e) {
            System.err.println(e.getMessage());
        }
        Set<Cart> expected = cartStorageSet;
        Set<Cart> actual = new HashSet<>();
        actual.add(cart2);
        assertEquals(expected, actual);
    }

}
