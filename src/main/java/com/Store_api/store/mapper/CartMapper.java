package com.Store_api.store.mapper;

import com.Store_api.store.dto.Cart.CartDto;
import com.Store_api.store.dto.Cart.CartItemDto;
import com.Store_api.store.entities.Cart;
import com.Store_api.store.entities.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.jpa.repository.Query;

@Mapper(componentModel = "spring")

public interface CartMapper {
    @Mapping(target = "cartItem",source = "cartItems")
    @Mapping(target = "totalPrice",expression = "java(cart.getTotalPrice())")
    CartDto toDto(Cart cart);
    @Mapping(source = "product", target = "cartProductDto")
    @Mapping(target = "totalPrice",expression = "java(cartItem.getTotalPrice())")
    CartItemDto toDto(CartItem cartItem);
}
