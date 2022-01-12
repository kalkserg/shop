package com.example.shop.service;

import com.example.shop.model.Product;

import java.util.Set;

public interface ProductService {
    Product createProduct(Product Product);
    Product updateProduct(Product Product);
    boolean deleteProduct(Product Product);
    Product getProductById(int id) throws MyException;
    Set<Product> getAllProducts();
}
