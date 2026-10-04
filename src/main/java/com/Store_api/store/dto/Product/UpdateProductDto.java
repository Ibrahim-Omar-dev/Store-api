package com.Store_api.store.dto.Product;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class UpdateProductDto {
    private String name;

    private String description;

    private BigDecimal price;

    private Byte categoryId;
}
