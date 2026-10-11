package com.Store_api.store.controllers;

import com.Store_api.store.Enum.OrderStatus;
import com.Store_api.store.Services.AuthServices;
import com.Store_api.store.Services.CartService;
import com.Store_api.store.Services.CheckoutServices;
import com.Store_api.store.dto.Checkout.CheckoutRequest;
import com.Store_api.store.dto.Checkout.CheckoutResponse;
import com.Store_api.store.entities.Order;
import com.Store_api.store.entities.OrderItem;
import com.Store_api.store.repositories.CartRepository;
import com.Store_api.store.repositories.OrderRepository;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.server.authentication.AuthenticationConverterServerWebExchangeMatcher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/checkout")
public class CheckoutController {
    private final CheckoutServices checkoutServices;
    @PostMapping()
    public ResponseEntity<CheckoutResponse>  Checkout(@Valid @RequestBody CheckoutRequest checkoutRequest)
    {
        var checkoutResponse = checkoutServices.checkout(checkoutRequest);
        return ResponseEntity.ok(checkoutResponse);
    }
}
