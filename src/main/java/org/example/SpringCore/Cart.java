package org.example.SpringCore;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Product> cartItems;
    public Cart() {
        this.cartItems = new ArrayList<>();
    }

    public void addToCart(Product product) {
        cartItems.add(product);
    }

    public void removeFromCart(long productId) {
        cartItems.removeIf(product -> product.getId() == productId);
    }

    public List<Product> getCartItems() {
        return cartItems;
    }
}
