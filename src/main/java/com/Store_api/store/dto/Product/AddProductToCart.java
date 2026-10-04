package com.Store_api.store.dto.Product;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddProductToCart {
    @NotNull
    private Long productId;
}
