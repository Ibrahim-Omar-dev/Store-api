package com.Store_api.store.dto.Cart;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
@Data
public class CartDto {
    private UUID id;
    private Date dateCreated;
    private List<CartItemDto> cartItem=new ArrayList<>();
    private BigDecimal totalPrice=BigDecimal.ZERO;
}
