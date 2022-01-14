package com.example.shop.controller;

import com.example.shop.model.Product;
import com.example.shop.service.MyException;
import com.example.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class ProductController {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/product/createProduct")
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @PostMapping("/product/updateProduct")
    public Product updateProduct(@RequestBody Product product) {
        return productService.updateProduct(product);
    }

    @PostMapping("/product/deleteProduct")
    public boolean deleteProduct(@RequestBody Product product) {
        try {
            return productService.deleteProduct(product);
        } catch (MyException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    @PostMapping(value = "/product/getAllProducts")
    @ResponseBody
    public Set<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping(value = "/product/{id}")
    public Product getProduct(@PathVariable String id) {
        try {
            return productService.getProductById(Integer.parseInt(id));
        } catch (MyException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }
}
