package org.example.SpringCore;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductRepository {
    private List<Product> products;

    public ProductRepository() {
        this.products = new ArrayList<>();
        products.add(new Product(1, "Product1", 20.0));
        products.add(new Product(2, "Product2", 30.0));
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public Optional<Product> getProductById(long id) {
        return products.stream().filter(product -> product.getId() == id).findFirst();
    }
}

