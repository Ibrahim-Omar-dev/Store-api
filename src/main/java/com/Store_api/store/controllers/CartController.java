package com.Store_api.store.controllers;

import com.Store_api.store.Services.CartService;
import com.Store_api.store.dto.Cart.CartDto;
import com.Store_api.store.dto.Cart.CartItemDto;
import com.Store_api.store.dto.Cart.UpdateCartItemRequest;
import com.Store_api.store.dto.Product.AddProductToCart;
import com.Store_api.store.entities.Cart;
import com.Store_api.store.mapper.CartMapper;
import com.Store_api.store.mapper.ProductMapper;
import com.Store_api.store.repositories.CartRepository;
import com.Store_api.store.repositories.ProductRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.security.Provider;
import java.util.Map;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/carts")
public class CartController {
    private final CartRepository cartRepository;
    private final CartMapper cartMapper;
    private final ProductRepository productRepository;
    private  final CartService cartService;

    @PostMapping
    public ResponseEntity<CartDto> createCart(UriComponentsBuilder uriComponentsBuilder) {
        var cartDto= cartService.createCart();
        var uri = uriComponentsBuilder
                .path("/carts/{id}")
                .buildAndExpand(cartDto.getId())
                .toUri();
        return ResponseEntity.created(uri).body(cartDto);
    }

    @PostMapping("/{cartId}/items")
    public ResponseEntity<CartItemDto> addToCart(@PathVariable UUID cartId, @RequestBody AddProductToCart request) {
        var cartItemDto=cartService.addCart(cartId,request.getProductId());
        return ResponseEntity.status(HttpStatus.CREATED).body(cartItemDto);
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<CartDto> getCartItem(@PathVariable UUID cartId) {
        var cartDto=cartService.getCartById(cartId);
        return ResponseEntity.ok().body(cartDto);
    }

    @PutMapping("/{cartId}/items/{productId}")
    public ResponseEntity<?> updateCartItem(@PathVariable UUID cartId
            , @PathVariable Long productId
            , @Valid @RequestBody UpdateCartItemRequest request) {
        var cartItemDto=cartService.updateCart(cartId,productId,request.getQuantity());
        return ResponseEntity.ok(cartItemDto);
    }

    @DeleteMapping("/{cartId}/items/{productId}")
    public ResponseEntity<?> deleteCartItem(@PathVariable UUID cartId, @PathVariable Long productId) {
        cartService.removeCartItem(cartId,productId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{cartId}/items")
    public ResponseEntity<?> clearCartItem(@PathVariable UUID cartId) {

        return ResponseEntity.noContent().build();
    }
}
