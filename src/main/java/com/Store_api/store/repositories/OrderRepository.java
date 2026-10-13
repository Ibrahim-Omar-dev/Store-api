package com.Store_api.store.repositories;

import com.Store_api.store.entities.Cart;
import com.Store_api.store.entities.Order;
import com.Store_api.store.entities.User;
import org.hibernate.validator.constraints.UUID;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @EntityGraph(attributePaths = "items.product")
    @Query("SELECT o FROM Order o WHERE o.user = :user")
    List<Order> getAllByUser(@Param("user") User user);

    @EntityGraph(attributePaths = "items.product")
    @Query("SELECT o FROM Order o WHERE o.id = :orderId")
    Optional<Order> getOrderWithItem(@Param("orderId") Long orderId);
}