package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private List<Product> products;

    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody Product newProduct) {
        products.add(newProduct);
        return ResponseEntity.ok("Product created successfully");
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable long productId) {
        return products.stream()
                .filter(product -> product.getId() == productId)
                .findFirst()
                .map(product -> ResponseEntity.ok().body(product))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return products;
    }

    @DeleteMapping("/{productId}")
    public String deleteProduct(@PathVariable long productId) {
        boolean removed = products.removeIf(product -> product.getId() == productId);
        return removed ? "Product deleted successfully" : "Product not found";
    }
}
