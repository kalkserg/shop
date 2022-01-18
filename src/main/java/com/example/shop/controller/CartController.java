package com.example.shop.controller;

import com.example.shop.model.Cart;
import com.example.shop.model.Product;
import com.example.shop.service.CartService;
import com.example.shop.service.MyException;
import com.example.shop.service.PersonService;
import com.example.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
public class CartController {

    private PersonService personService;
    private CartService cartService;
    private ProductService productService;

    @Autowired
    public void setCartService(PersonService personService, CartService cartService, ProductService productService) {
        this.personService = personService;
        this.cartService = cartService;
        this.productService = productService;
    }

    @PostMapping(value = "/cart/{idCart}/addProduct/{idProduct}")
    public BigDecimal buy(@PathVariable String idCart, @PathVariable String idProduct) {
        Product product = null;
        try {
            product = productService.getProductById(Integer.parseInt(idProduct));
        } catch (MyException e) {
            System.err.println(e.getMessage());
        }

        if (product != null) {
            try {
                Cart cart = cartService.getCartById(Integer.parseInt(idCart));
                return cart.addProduct(product);
            } catch (MyException e) {
                System.err.println(e.getMessage());
            }
        }
        return null;
    }

    @PostMapping(value = "cart/{idCart}/deleteProduct/{idProduct}")
    public BigDecimal cancel(@PathVariable String idCart, @PathVariable String idProduct) {
        Product product = null;
        try {
            product = productService.getProductById(Integer.parseInt(idProduct));
        } catch (MyException e) {
            System.err.println(e.getMessage());
        }

        if (product != null) {
            Cart cart = null;
            try {
                cart = cartService.getCartById(Integer.parseInt(idCart));
            } catch (MyException e) {
                System.err.println(e.getMessage());
            }
            return cart.delProduct(product);
        }
        return null;
    }

    @PostMapping(value = "/cart/{idCart}/getAllProducts")
    public List<Product> getAllProducts(@PathVariable String idCart) {
        Cart cart = null;
        try {
            cart = cartService.getCartById(Integer.parseInt(idCart));
        } catch (MyException e) {
            System.err.println(e.getMessage());
        }
        if (cart != null) return cart.getProducts();
        return new ArrayList<Product>();
    }

    @PostMapping(value = "/cart/getAllCarts")
    public Set<Cart> getAllCarts() {
        List<Cart> carts = new ArrayList<>();
        return cartService.getAllCarts();
    }
}
