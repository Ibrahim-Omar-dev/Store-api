package com.Store_api.store.controllers;

import com.Store_api.store.dto.ProductDto;
import com.Store_api.store.entities.Product;
import com.Store_api.store.mapper.ProductMapper;
import com.Store_api.store.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;

    @GetMapping
    public List<ProductDto> getProducts(
            @RequestParam(required = false) Byte categoryId) {

        List<Product> products;

        if (categoryId != null) {
            products = productRepository.findByCategoryId(categoryId);
        } else {
            products = productRepository.findAllWithCategory();
        }

        return products.stream()
                .map(productMapper::toDto)
                .toList();
    }
}
