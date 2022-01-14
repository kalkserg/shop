package com.example.shop.controller;

import com.example.shop.model.Cart;
import com.example.shop.model.Person;
import com.example.shop.model.Product;
import com.example.shop.service.CartService;
import com.example.shop.service.MyException;
import com.example.shop.service.PersonService;
import com.example.shop.service.ProductService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
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
    public Float buy(@PathVariable String idCart, @PathVariable String idProduct) {
//        Person person = personService.getPersonById(Integer.parseInt(idPerson));
//        List<Integer> idCarts = person.getIdCarts();
//        long count = idCarts.stream().filter(i -> i.equals(idCart)).count();
        Cart cart = null;
        Product product = null;
        try {
            product = productService.getProductById(Integer.parseInt(idProduct));
        }catch (MyException e){
            System.err.println(e.getMessage());
        }

        if(product!=null) {
            try {
                cart = cartService.getCartById(Integer.parseInt(idCart));
                return cart.addProduct(product);
            }catch (MyException e){
                System.err.println(e.getMessage());
            }
        }
        return null;
    }

    @PostMapping(value = "cart/{idCart}/deleteProduct/{idProduct}")
    public Float cancel(@PathVariable String idCart, @PathVariable String idProduct) {
        Product product = null;
        try {
            product = productService.getProductById(Integer.parseInt(idProduct));
        } catch (MyException e) {
            System.err.println(e.getMessage());
        }

        if(product!=null) {
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
        if(cart!=null) return cart.getProducts();
        return new ArrayList<Product>();
    }

    @PostMapping(value = "/cart/getAllCarts")
    public Set<Cart> getAllCarts() {
        List<Cart> carts = new ArrayList<>();
        return cartService.getAllCarts();
    }

//    @PostMapping(value = "/carts/{idPerson}/{idCart}")
//    public List<Product> bought(@PathVariable String idPerson, @PathVariable String idCart) {
//        Person person = null;
//        try {
//            person = personService.getPersonById(Integer.parseInt(idPerson));
//        } catch (MyException e) {
//            System.err.println(e.getMessage());
//        }
//        List<Integer> idCarts = person.getIdCarts();
//        long count = idCarts.stream().filter(i -> i.equals(idCart)).count();
//        if(count>0) {
//            Cart cart = null;
//            try {
//                cart = cartService.getCartById(Integer.parseInt(idCart));
//            } catch (MyException e) {
//                System.err.println(e.getMessage());
//            }
//            return cart.getProducts();
//        }
//        return new ArrayList<Product>();
//    }

}
