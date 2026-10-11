package com.Store_api.store.Services;

import com.Store_api.store.Exception.CartNotFoundException;
import com.Store_api.store.Exception.cartEmptyException;
import com.Store_api.store.dto.Checkout.CheckoutRequest;
import com.Store_api.store.dto.Checkout.CheckoutResponse;
import com.Store_api.store.entities.Order;
import com.Store_api.store.repositories.CartRepository;
import com.Store_api.store.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
@AllArgsConstructor
@Service
public class CheckoutServices {
    private final CartRepository cartRepository;
    private final AuthServices authServices;
    private final OrderRepository orderRepository;
    private final CartService cartService;
    public CheckoutResponse checkout(CheckoutRequest checkoutRequest)
    {
        var cart = cartRepository.getCartWithItems(checkoutRequest.getCartId()).orElse(null);
        if(cart == null)
            throw new CartNotFoundException();
        if (cart.isEmpty())
            throw new cartEmptyException();
        var order= Order.fromCart(cart,authServices.getCurrentUser());
        orderRepository.save(order);
        cartService.clearCartItem(cart.getId());

        return new CheckoutResponse(order.getId());
    }
}
