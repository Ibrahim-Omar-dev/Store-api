package com.Store_api.store.repositories;

import com.Store_api.store.entities.Cart;
import com.Store_api.store.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
