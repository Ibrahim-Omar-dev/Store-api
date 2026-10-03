package com.Store_api.store.controllers;

import com.Store_api.store.dto.Product.CreateProductDto;
import com.Store_api.store.dto.Product.ProductDto;
import com.Store_api.store.dto.Product.UpdateProductDto;
import com.Store_api.store.entities.Product;
import com.Store_api.store.mapper.ProductMapper;
import com.Store_api.store.repositories.CategoryRepository;
import com.Store_api.store.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.mapstruct.ap.shaded.freemarker.core.ReturnInstruction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

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
    @PostMapping
    public ResponseEntity<ProductDto> createProduct(
            @RequestBody CreateProductDto createProductDto, UriComponentsBuilder uriComponentsBuilder) {

        var product = productMapper.toEntity(createProductDto);
        var category=categoryRepository.findById(createProductDto.getCategoryId()).orElse(null);
        if (category == null) {
            return ResponseEntity.badRequest().build();
        }

        product.setCategory(category);
        productRepository.save(product);

        var productDto = productMapper.toDto(product);

        var uri = uriComponentsBuilder
                .path("/products/{id}")
                .buildAndExpand(productDto.getId())
                .toUri();

        return ResponseEntity
                .created(uri)
                .body(productDto);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct
            (@PathVariable Long id,@RequestBody  UpdateProductDto UpdateProductDto)
    {
        var product = productRepository.findById(id).orElse(null);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        productMapper.update(UpdateProductDto,product);
        productRepository.save(product);
        return ResponseEntity.ok(productMapper.toDto(product));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        var product = productRepository.findById(id).orElse(null);
        if (product == null) {
            return  ResponseEntity.notFound().build();
        }
        productRepository.delete(product);
        return ResponseEntity.noContent().build();
    }
}
