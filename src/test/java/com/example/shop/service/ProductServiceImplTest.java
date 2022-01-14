package com.example.shop.service;

import com.example.shop.model.Product;
import com.example.shop.model.Product;
import com.example.shop.model.Product;
import com.example.shop.storage.ProductStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static com.example.shop.storage.ProductStorage.productStorageSet;
import static com.example.shop.storage.ProductStorage.productStorageSet;
import static org.junit.jupiter.api.Assertions.*;

class ProductServiceImplTest {

    ProductStorage productStorage;
    ProductServiceImpl productServiceImpl = new ProductServiceImpl(productStorage);

    Product product1;
    Product product2;
    
    @BeforeEach
    void create() {
        product1 = new Product(1, "Home", "Lamp", 12.1f);
        product2 = new Product(2, "Garden", "Tree", 34.3f);
        productServiceImpl.createProduct(product1);
        productServiceImpl.createProduct(product2);
    }

    @Test
    void createProductTest() {
        Set<Product> expected = productStorageSet;
        Set<Product> actual = new HashSet<>();
        actual.add(product1);
        actual.add(product2);
        assertEquals(expected, actual);
    }


    @Test
    void updateProductTest() {
        Product product1new = new Product(1, "Booo", "Booo", 90.9f);
        productServiceImpl.updateProduct(product1new);
        Set<Product> expected = productStorageSet;
        Set<Product> actual = new HashSet<>();
        actual.add(product1new);
        actual.add(product2);
        assertEquals(expected, actual);
    }

    @Test
    void deleteProductTest() {
        try {
            productServiceImpl.deleteProduct(product1);
        } catch (MyException e) {
            System.err.println(e.getMessage());
        }
        Set<Product> expected = productStorageSet;
        Set<Product> actual = new HashSet<>();
        actual.add(product2);
        assertEquals(expected, actual);
    }
}
