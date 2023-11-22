package org.example.SpringCore.AppConfig;


import org.example.SpringCore.ShoppingCartApp.Cart;
import org.example.SpringCore.ProductRepository.ProductRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public ProductRepository productRepository() {
        return new ProductRepository();
    }

    @Bean
    public Cart cart() {
        return new Cart();
    }
}
