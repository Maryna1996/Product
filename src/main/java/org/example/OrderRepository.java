package org.example;

import java.util.*;

public class OrderRepository {
    private Map<Long, Order> orders = new HashMap<>();
    private Long orderId;

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
        Order order = orders.get(newProduct.getId());
        if (order != null) {
            order.getProducts().add(newProduct);
        }
    }

    public Product getProductById(long productId) {
        for (Order order : orders.values()) {
            for (Product product : order.getProducts()) {
                if (product.getId() == productId) {
                    return product;
                }
            }
        }
        return null;
    }

    public boolean removeProduct(long productId) {
        for (Order order : orders.values()) {
            List<Product> products = order.getProducts();
            Iterator<Product> iterator = products.iterator();
            while (iterator.hasNext()) {
                Product product = iterator.next();
                if (product.getId() == productId) {
                    iterator.remove();
                    return true;
                }
            }
        }
        return false;
    }

    public List<Product> getAllProducts() {
        List<Product> allProducts = new ArrayList<>();
        for (Order order : orders.values()) {
            allProducts.addAll(order.getProducts());
        }
        return allProducts;
    }
}
