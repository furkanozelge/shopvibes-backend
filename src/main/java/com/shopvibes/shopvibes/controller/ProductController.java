package com.shopvibes.shopvibes.controller;

import com.shopvibes.shopvibes.model.Product;
import com.shopvibes.shopvibes.service.ProductService;
import com.shopvibes.shopvibes.security.JwtTokenUtil; // JwtTokenUtil import edilmelidir
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @GetMapping
    public ResponseEntity<List<Product>> getProducts(@RequestHeader("Authorization") String token) {
        String email = jwtTokenUtil.getUsernameFromToken(token.substring(7));
        return ResponseEntity.ok(productService.getAllProducts(email));
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product, @RequestHeader("Authorization") String token) {
        String email = jwtTokenUtil.getUsernameFromToken(token.substring(7));
        product.setEmail(email);
        return ResponseEntity.ok(productService.addProduct(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable String id, @RequestBody Product product) {
        return ResponseEntity.ok(productService.updateProduct(id, product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/delete")
    public ResponseEntity<Void> deleteProducts(@RequestBody List<String> ids) {
        productService.deleteProducts(ids);
        return ResponseEntity.noContent().build();
    }
}