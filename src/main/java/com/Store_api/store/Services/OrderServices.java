package com.Store_api.store.Services;

import com.Store_api.store.Exception.UserNotFoundException;
import com.Store_api.store.dto.Order.OrderDto;
import com.Store_api.store.mapper.OrderMapper;
import com.Store_api.store.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.access.AccessDeniedException;
import java.util.List;

@AllArgsConstructor
@Service
public class OrderServices {
    private final OrderRepository orderRepository;
    private final AuthServices  authServices;
    private final OrderMapper  orderMapper;
    public List<OrderDto> getAllOrders() {
        var user=authServices.getCurrentUser();
        if (user==null)
            throw new UserNotFoundException();
        var order=orderRepository.getAllByUser(user);
        return order.stream().map(orderMapper::toDto).toList();
    }
    public OrderDto getOrderById(Long orderId) {
        var order=orderRepository.getOrderWithItem(orderId)
                .orElseThrow(UserNotFoundException::new);

        var user=authServices.getCurrentUser();
        if(!order.getUser().getId().equals(user.getId()))
            throw new AccessDeniedException("You don't have access to this order");
        return orderMapper.toDto(order);
    }
}
