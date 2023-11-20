package org.example;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class OrderRepository {
    private Map<Long, Order> orders;
    private Map<Long, Product> products;
    public OrderRepository() {
        this.orders = new HashMap<>();
        this.products = new HashMap<>();
    }

    public Order getOrderById(long orderId) {
        return orders.get(orderId);
    }

    public List<Order> getAllOrders() {
        return new ArrayList<>(orders.values());
    }

    public void addOrder(Order order) {
        orders.put(order.getId(), order);
    }

    public void addProduct(Product newProduct) {
        products.put(newProduct.getId(), newProduct);
    }

    public Product getProductById(long productId) {
        return products.get(productId);
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }

    public boolean removeProduct(long productId) {
        return products.remove(productId) != null;
    }
}
