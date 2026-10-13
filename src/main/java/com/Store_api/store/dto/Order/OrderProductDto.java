package com.Store_api.store.dto.Order;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter @Setter
public class OrderProductDto {
    private Long id;
    private String name;
    private BigDecimal price;
}
