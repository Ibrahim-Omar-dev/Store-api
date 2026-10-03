package com.Store_api.store.mapper;

import com.Store_api.store.dto.Product.CreateProductDto;
import com.Store_api.store.dto.Product.ProductDto;
import com.Store_api.store.dto.Product.UpdateProductDto;
import com.Store_api.store.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(source = "category.id", target = "categoryId")
    ProductDto toDto(Product product);

    @Mapping(target = "category", ignore = true)
    Product toEntity(CreateProductDto  createProductDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", ignore = true)
    void update(UpdateProductDto UpdateProductDto, @MappingTarget Product product);
}