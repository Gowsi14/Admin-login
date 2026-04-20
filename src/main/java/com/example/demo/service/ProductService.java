package com.example.demo.service;

import com.example.demo.dto.ProductResponse;
import com.example.demo.entity.Product;
import com.example.demo.repo.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    private ProductResponse toResponse(Product p) {
        return ProductResponse.builder()
                .id(p.getId())
                .name(p.getName())
                .category(p.getCategory())
                .monthlyPrice(p.getMonthlyPrice())
                .totalPurchased(p.getTotalPurchased())
                .build();
    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll()
                .stream().map(this::toResponse)
                .collect(Collectors.toList());
    }

    public ProductResponse getById(Long id) {
        Product p = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return toResponse(p);
    }

    public ProductResponse create(Product product) {
        return toResponse(productRepository.save(product));
    }

    public ProductResponse update(Long id, Product updated) {
        Product p = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        p.setName(updated.getName());
        p.setCategory(updated.getCategory());
        p.setMonthlyPrice(updated.getMonthlyPrice());
        p.setTotalPurchased(updated.getTotalPurchased());
        return toResponse(productRepository.save(p));
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}