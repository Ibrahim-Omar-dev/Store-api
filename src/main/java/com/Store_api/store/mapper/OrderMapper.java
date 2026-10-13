package com.Store_api.store.mapper;

import com.Store_api.store.dto.Order.OrderDto;
import com.Store_api.store.entities.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDto toDto(Order order);
}