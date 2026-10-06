package com.Store_api.store.Services;

import com.Store_api.store.Exception.CartNotFoundException;
import com.Store_api.store.Exception.ProductNotFoundException;
import com.Store_api.store.dto.Cart.CartDto;
import com.Store_api.store.dto.Cart.CartItemDto;
import com.Store_api.store.dto.Cart.UpdateCartItemRequest;
import com.Store_api.store.entities.Cart;
import com.Store_api.store.mapper.CartMapper;
import com.Store_api.store.repositories.CartRepository;
import com.Store_api.store.repositories.ProductRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final CartMapper cartMapper;
    private final ProductRepository productRepository;
    public CartDto createCart()
    {
        var cart = new Cart();
        cartRepository.save(cart);
        return cartMapper.toDto(cart);
    }
    public CartItemDto addCart(UUID cartId, Long productId)
    {
        var cart = cartRepository.getCartWithItems(cartId).orElse(null);
        if (cart == null)
            throw  new CartNotFoundException();
        var product = productRepository.findById(productId).orElse(null);
        if (product == null)
            throw new ProductNotFoundException();

        var cartItem = cart.addItem(product);
        cartRepository.save(cart);
        return cartMapper.toDto(cartItem);
    }
    public CartDto getCartById(UUID  cartId)
    {
        var cart = cartRepository.getCartWithItems(cartId).orElse(null);
        if (cart == null)
            throw  new CartNotFoundException();
        return cartMapper.toDto(cart);
    }
    public CartDto updateCart(UUID cartId, Long productId,Integer quantity) {
        var cart = cartRepository.getCartWithItems(cartId).orElse(null);
        if (cart == null)
            throw new CartNotFoundException();
        var cartItem = cart.getItems(productId);
        if (cartItem == null)
            throw new ProductNotFoundException();
        cartItem.setQuantity(quantity);
        cartRepository.save(cart);
        return cartMapper.toDto(cart);
    }
    public void removeCartItem(UUID cartId, Long productId)
    {
        var cart = cartRepository.getCartWithItems(cartId).orElse(null);
        if (cart == null)
            throw new CartNotFoundException();
        cart.removeItem(productId);
        cartRepository.save(cart);
    }
    public void clearCartItem(UUID cartId)
    {
        var cart = cartRepository.getCartWithItems(cartId).orElse(null);
        if (cart == null)
            throw new CartNotFoundException();
        cart.clear();
        cartRepository.save(cart);
    }
}
