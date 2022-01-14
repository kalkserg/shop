package com.example.shop.storage;

import com.example.shop.model.Cart;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CartStorage {
    public static Set<Cart> cartStorageSet = new HashSet<Cart>() {

    };
}
