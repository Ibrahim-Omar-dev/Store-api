package com.Store_api.store.Services;

import com.Store_api.store.Exception.CategoryNotFoundException;
import com.Store_api.store.Exception.ProductNotFoundException;
import com.Store_api.store.dto.Product.CreateProductDto;
import com.Store_api.store.dto.Product.ProductDto;
import com.Store_api.store.dto.Product.UpdateProductDto;
import com.Store_api.store.entities.Product;
import com.Store_api.store.mapper.ProductMapper;
import com.Store_api.store.repositories.CategoryRepository;
import com.Store_api.store.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
@Service
@AllArgsConstructor
public class ProductServices {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;
    public List<ProductDto> getProducts(Byte categoryId) {

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
    public ProductDto createProduct(CreateProductDto createProductDto) {
        var product = productMapper.toEntity(createProductDto);
        var category = categoryRepository.findById(createProductDto.getCategoryId()).orElse(null);
        if (category == null) {
            throw new CategoryNotFoundException();
        }
        product.setCategory(category);
        productRepository.save(product);

        return productMapper.toDto(product);
    }
    public ProductDto updateProduct(Long id, UpdateProductDto UpdateProductDto)
    {
        var product = productRepository.findById(id).orElse(null);
        if (product == null) {
            throw new ProductNotFoundException();
        }
        productMapper.update(UpdateProductDto,product);
        productRepository.save(product);
        return productMapper.toDto(product);
    }
    public void deleteProduct( Long id) {
        var product = productRepository.findById(id).orElse(null);
        if (product == null) {
            throw new ProductNotFoundException();
        }
        productRepository.delete(product);
    }
}
