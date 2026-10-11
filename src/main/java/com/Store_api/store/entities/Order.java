package com.Store_api.store.entities;

import com.Store_api.store.Enum.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="customer_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    @Column(name="created_at")
    private LocalDateTime createdAt;
    @Column(name="total_price")
    private BigDecimal totalPrice;

    @OneToMany(mappedBy = "order",cascade = CascadeType.PERSIST)
    private Set<OrderItem> items = new LinkedHashSet<>();

    public static Order fromCart(Cart cart , User user) {
        var order=new Order();
        order.setTotalPrice(cart.getTotalPrice());
        order.setStatus(OrderStatus.PENDING);
        order.setUser(user);
        order.setCreatedAt(LocalDateTime.now());

        cart.getCartItems().forEach(item->{
            var orderItem=new OrderItem(order,item.getProduct(),item.getQuantity());
            order.items.add(orderItem);
        });
        return order;
    }
}
