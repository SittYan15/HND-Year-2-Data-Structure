package com.example.NTT.service;

import com.example.NTT.entity.Product;
import com.example.NTT.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public CompletableFuture<Boolean> saveProduct(List<Product> products) {
        return CompletableFuture.supplyAsync(() -> {
            productRepository.deleteAll();
            productRepository.saveAll(products);
            return true;
        });
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }
}
