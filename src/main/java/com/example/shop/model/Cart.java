package com.example.shop.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class Cart {

    private Integer id;
    private List<Product> products = new ArrayList<Product>();
    private float sum = 0;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return Objects.equals(id, cart.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Float addProduct(Product product) {
        try {
            products.add(product);
        } catch (Exception ex) {
            System.out.println("No such product!");
        }
        sum = sum();
        return sum;
    }

    public Float delProduct(Product product) {
        products.remove(product);
        this.sum = sum();
        return this.sum;
    }

    public float sum() {
        float sumPrice = 0;
        for (Product p : products) {
            sumPrice += p.getPrice();
        }
        return sumPrice;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", products=" + products +
                ", sum=" + sum +
                '}';
    }
}
