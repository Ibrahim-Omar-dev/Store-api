package com.Store_api.store.dto.Order;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter @Setter
public class OrderItemDto {
    private OrderProductDto  orderProductDto;
    private Integer quantity;
    private BigDecimal totalPrice;
}
