package com.shopvibes.shopvibes.service;

import com.shopvibes.shopvibes.model.Product;
import com.shopvibes.shopvibes.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts(String email) {
        return productRepository.findByEmail(email);
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(String id, Product product) {
        product.setId(id);
        return productRepository.save(product);
    }

    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }

    public void deleteProducts(List<String> ids) {
        for (String id : ids) {
            deleteProduct(id);
        }
    }
}