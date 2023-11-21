package org.example.SpringCore;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ShoppingCartApp {
    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class)) {
            ProductRepository productRepository = context.getBean(ProductRepository.class);
            Cart cart = context.getBean(Cart.class);

            displayMenu();

            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();

            while (choice != 0) {
                switch (choice) {
                    case 1:
                        displayAllProducts(productRepository.getAllProducts());
                        break;
                    case 2:
                        addToCart(scanner, productRepository, cart);
                        break;
                    case 3:
                        removeFromCart(scanner, cart);
                        break;
                    case 4:
                        displayCart(cart);
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }

                displayMenu();
                choice = scanner.nextInt();
            }
        }
    }

    private static void displayMenu() {
        System.out.println("===== Shopping Cart Menu =====");
        System.out.println("1. Display All Products");
        System.out.println("2. Add to Cart");
        System.out.println("3. Remove from Cart");
        System.out.println("4. Display Cart");
        System.out.println("0. Exit");
        System.out.println("=============================");
        System.out.print("Enter your choice: ");
    }

    private static void displayAllProducts(List<Product> products) {
        System.out.println("===== All Products =====");
        for (Product product : products) {
            System.out.println(product);
        }
        System.out.println("=========================");
    }

    private static void addToCart(Scanner scanner, ProductRepository productRepository, Cart cart) {
        System.out.print("Enter the product ID to add to cart: ");
        long productId = scanner.nextLong();
        Optional<Product> product = productRepository.getProductById(productId);

        if (product.isPresent()) {
            cart.addToCart(product.get());
            System.out.println("Product added to cart successfully.");
        } else {
            System.out.println("Product not found with ID: " + productId);
        }
    }

    private static void removeFromCart(Scanner scanner, Cart cart) {
        System.out.print("Enter the product ID to remove from cart: ");
        long productId = scanner.nextLong();
        cart.removeFromCart(productId);
        System.out.println("Product removed from cart successfully.");
    }

    private static void displayCart(Cart cart) {
        System.out.println("===== Shopping Cart =====");
        List<Product> cartItems = cart.getCartItems();
        if (cartItems.isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            for (Product product : cartItems) {
                System.out.println(product);
            }
        }
        System.out.println("=========================");
    }
}



