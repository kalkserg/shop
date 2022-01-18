package com.example.shop.service;

import com.example.shop.model.Product;
import com.example.shop.storage.ProductStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

import static com.example.shop.storage.ProductStorage.productStorageSet;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductStorage productStorage;

    @Autowired
    public ProductServiceImpl(ProductStorage productStorage) {
        this.productStorage = productStorage;
    }

    @Override
    public Product createProduct(Product product) {
        boolean isNewProduct = productStorageSet.add(product);
        if (isNewProduct) {
            return product;
        }
        return null;
    }

    @Override
    public Product updateProduct(Product newProduct) {
        Product oldProduct= null;
        try {
            oldProduct = productStorageSet.stream().filter(p -> p.getId().equals(newProduct.getId())).findFirst().orElseThrow();
        }catch (Exception e) {
            return new Product();
        }
        productStorageSet.remove(oldProduct);
        productStorageSet.add(newProduct);
        return newProduct;
    }

    @Override
    public boolean deleteProduct(Product product) throws MyException {
        try {
            return productStorageSet.remove(product);
        } catch (Exception ex) {
            throw new MyException("Wrong person");
        }
    }

    @Override
    public Product getProductById(int id) throws MyException {
        try {
            return productStorageSet.stream().filter(p -> p.getId().equals(id)).findFirst().orElseThrow();
        } catch (Exception ex) {
            throw new MyException("Wrong idProduct");
        }
    }

    @Override
    public Set<Product> getAllProducts() {
        return productStorageSet;
    }
}
