package com.example.shop.service;

import com.example.shop.model.Product;
import org.springframework.stereotype.Service;

import java.util.Set;

import static com.example.shop.storage.ProductStorage.productStorageSet;

@Service
public class ProductServiceImpl implements ProductService{
    public Product createProduct(Product product) {
        boolean isNewProduct = productStorageSet.add(product);
        if(isNewProduct) {
            return product;
        }
        return null;
    }

    public Product updateProduct(Product product) {
        Product newProduct = product;
        Product oldProduct = productStorageSet.stream().filter(p -> p.equals(product)).findFirst().get();
        oldProduct = newProduct;
        return newProduct;
    }

    public boolean deleteProduct(Product Product) {
        return productStorageSet.remove(Product);
    }

    public Product getProductById(int id) throws MyException {
        try {
            return productStorageSet.stream().filter(p -> p.getId().equals(id)).findFirst().get();
        }catch (Exception ex){
            throw new MyException("Wrong idProduct");
        }
    }

    @Override
    public Set<Product> getAllProducts() {
        return productStorageSet;
    }
}
