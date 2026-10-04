package com.Store_api.store.controllers;

import com.Store_api.store.dto.Cart.CartDto;
import com.Store_api.store.dto.Cart.CartItemDto;
import com.Store_api.store.dto.Cart.UpdateCartItemRequest;
import com.Store_api.store.dto.Product.AddProductToCart;
import com.Store_api.store.dto.Product.UpdateProductDto;
import com.Store_api.store.entities.Cart;
import com.Store_api.store.entities.CartItem;
import com.Store_api.store.mapper.CartMapper;
import com.Store_api.store.repositories.CartRepository;
import com.Store_api.store.repositories.ProductRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/carts")
public class CartController {
    private final CartRepository cartRepository;
    private final CartMapper cartMapper;
    private final ProductRepository productRepository;

    @PostMapping
    public ResponseEntity<CartDto> createCart(
            UriComponentsBuilder uriComponentsBuilder) {

        var cart = new Cart();
        cartRepository.save(cart);

        var cartDto = cartMapper.toDto(cart);

        var uri = uriComponentsBuilder
                .path("/carts/{id}")
                .buildAndExpand(cart.getId())
                .toUri();

        return ResponseEntity.created(uri).body(cartDto);
    }

    @PostMapping("/{cartId}/items")
    public ResponseEntity<CartItemDto> addToCart(@PathVariable UUID cartId, @RequestBody AddProductToCart request) {
        var cart = cartRepository.findById(cartId).orElse(null);
        if (cart == null)
            return ResponseEntity.notFound().build();
        var product = productRepository.findById(request.getProductId()).orElse(null);
        if (product == null)
            return ResponseEntity.badRequest().build();

        var cartItem = cart.addItem(product);
        cartRepository.save(cart);
        var cartItemDto = cartMapper.toDto(cartItem);
        return ResponseEntity.status(HttpStatus.CREATED).body(cartItemDto);
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<CartDto> getCartItem(@PathVariable UUID cartId) {
        var cart = cartRepository.getCartWithItems(cartId).orElse(null);
        if (cart == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(cartMapper.toDto(cart));
    }

    @PutMapping("/{cartId}/items/{productId}")
    public ResponseEntity<?> updateCartItem(@PathVariable UUID cartId
            , @PathVariable Long productId
            , @Valid @RequestBody UpdateCartItemRequest request) {
        var cart = cartRepository.getCartWithItems(cartId).orElse(null);
        if (cart == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    Map.of("error", "cart not found")
            );
        var cartItem = cart.getItems(productId);

        if (cartItem == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    Map.of("error", "product was not found in cart")
            );
        cartItem.setQuantity(request.getQuantity());
        cartRepository.save(cart);
        return ResponseEntity.ok(cartMapper.toDto(cart));
    }

    @DeleteMapping("/{cartId}/items/{productId}")
    public ResponseEntity<?> deleteCartItem(@PathVariable UUID cartId, @PathVariable Long productId) {
        var cart = cartRepository.getCartWithItems(cartId).orElse(null);
        if (cart == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    Map.of("error", "cart not found")
            );
        cart.removeItem(productId);
        cartRepository.save(cart);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{cartId}/items/")
    public ResponseEntity<?> clearCartItem(@PathVariable UUID cartId) {
        var cart = cartRepository.getCartWithItems(cartId).orElse(null);
        if (cart == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    Map.of("error", "cart not found")
            );
        cart.clear();
        cartRepository.save(cart);
        return ResponseEntity.noContent().build();
    }
}
