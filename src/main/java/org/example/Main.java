package org.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Product product1 = new Product(1, "Product 1", 10.0);
        Product product2 = new Product(2, "Product 2", 20.0);

        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);

        Order order = new Order(1, new Date(), 30.0, products);

        OrderRepository orderRepository = new OrderRepository();
        orderRepository.addOrder(order);

        List<Order> allOrders = orderRepository.getAllOrders();
        System.out.println("All Orders: " + allOrders);

        List<Product> allProducts = orderRepository.getAllProducts();
        System.out.println("All Products: " + allProducts);

        long productIdToDelete = 1;
        boolean removed = orderRepository.removeProduct(productIdToDelete);
        if (removed) {
            System.out.println("Product with ID " + productIdToDelete + " removed successfully");
        } else {
            System.out.println("Product with ID " + productIdToDelete + " not found");
        }

        List<Product> updatedProducts = orderRepository.getAllProducts();
        System.out.println("Updated Products: " + updatedProducts);
    }
}

