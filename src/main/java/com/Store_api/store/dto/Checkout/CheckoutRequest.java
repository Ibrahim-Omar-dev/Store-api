package com.Store_api.store.dto.Checkout;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;
@Data
public class CheckoutRequest {
    @NotNull(message = "cartId is required")
    private UUID cartId;
}
