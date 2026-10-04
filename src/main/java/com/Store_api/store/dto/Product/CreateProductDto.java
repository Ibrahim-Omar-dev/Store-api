package com.Store_api.store.dto.Product;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@NoArgsConstructor
 public class CreateProductDto {
    private String name;
    private String description;
    private BigDecimal price;
    private Byte categoryId;
}