package com.Store_api.store.dto.Product;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@NoArgsConstructor
 public class CreateProductDto {
    @NotNull(message = "name is Required")
    private String name;
    private String description;
    @NotNull(message = "price is Required")

    private BigDecimal price;
    private Byte categoryId;
}