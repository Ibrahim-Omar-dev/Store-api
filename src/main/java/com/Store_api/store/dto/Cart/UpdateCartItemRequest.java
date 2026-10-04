package com.Store_api.store.dto.Cart;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateCartItemRequest {
    @NotNull(message = "Quantity is reqired")
    @Min(value = 1,message = "Quantity must be greater than zero")
    @Max(value = 1000,message = "Quantity must be lower than 1000")
    private Integer quantity;
}
