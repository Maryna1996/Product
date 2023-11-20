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
    private OrderRepository orderRepository;

    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody Product newProduct) {
        orderRepository.addProduct(newProduct);
        return ResponseEntity.ok("Product created successfully");
    }

    @GetMapping("/{productId}")
    public ResponseEntity<?> getProductById(@PathVariable long productId) {
        Product product = orderRepository.getProductById(productId);
        if (product != null) {
            return ResponseEntity.ok().body(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return orderRepository.getAllProducts();
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable long productId) {
        boolean removed = orderRepository.removeProduct(productId);
        return removed
                ? ResponseEntity.ok("Product deleted successfully")
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
    }
}
