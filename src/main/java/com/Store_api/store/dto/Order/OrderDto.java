package com.Store_api.store.dto.Order;

import com.Store_api.store.Enum.OrderStatus;
import com.Store_api.store.entities.OrderItem;
import com.Store_api.store.entities.User;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
@Getter @Setter
public class OrderDto {
    private  Long id;
    private OrderStatus status;
    private LocalDateTime createdAt;
    private List<OrderItemDto> items ;
    private BigDecimal totalPrice;
}
