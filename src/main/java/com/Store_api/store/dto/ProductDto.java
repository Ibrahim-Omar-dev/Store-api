package com.Store_api.store.dto;

import com.Store_api.store.entities.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.mapstruct.Mapper;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class ProductDto {
    private Long id;

    private String name;

    private String description;

    private BigDecimal price;

    private Byte categoryId;
}
