package com.Store_api.store.repositories;

import com.Store_api.store.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface CartRepository extends JpaRepository<Cart,UUID> {
    @Query("""
    SELECT c
    FROM Cart c
    JOIN FETCH c.cartItems ci
    JOIN FETCH ci.product
    WHERE c.id = :cartId
""")
    Optional<Cart> getCartWithItems(@Param("cartId") UUID cartId);
}
