package com.example.shop.storage;

import com.example.shop.model.Product;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ProductStorage {
    public static Set<Product> productStorageSet = new HashSet<Product>() {

    };
}
