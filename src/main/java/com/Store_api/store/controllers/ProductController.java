package com.Store_api.store.controllers;

import com.Store_api.store.Services.ProductServices;
import com.Store_api.store.dto.Product.CreateProductDto;
import com.Store_api.store.dto.Product.ProductDto;
import com.Store_api.store.dto.Product.UpdateProductDto;
import com.Store_api.store.entities.Product;
import com.Store_api.store.mapper.ProductMapper;
import com.Store_api.store.repositories.CategoryRepository;
import com.Store_api.store.repositories.ProductRepository;
import jakarta.validation.Valid;
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
    private final ProductServices productServices;

    @GetMapping
    public List<ProductDto> getProducts(
            @RequestParam(required = false) Byte categoryId) {

        return productServices.getProducts(categoryId);
    }
    @PostMapping
    public ResponseEntity<ProductDto> createProduct(
            @Valid  @RequestBody CreateProductDto createProductDto, UriComponentsBuilder uriComponentsBuilder) {
        var productDto=productServices.createProduct(createProductDto);

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
            (@PathVariable Long id,@Valid @RequestBody UpdateProductDto UpdateProductDto)
    {
        var productDto=productServices.updateProduct(id, UpdateProductDto);
        return ResponseEntity.ok(productDto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productServices.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
