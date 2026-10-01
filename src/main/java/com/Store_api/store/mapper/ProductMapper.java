package com.Store_api.store.mapper;

import com.Store_api.store.dto.ProductDto;
import com.Store_api.store.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(source = "category.id", target = "categoryId")
    ProductDto toDto(Product product);
}