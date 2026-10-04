package com.Store_api.store.dto.Cart;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class CartItemDto {
    private CartProductDto cartProductDto;
    private String quantity;
    private BigDecimal totalPrice;
}
