package com.Store_api.store.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="order_id",nullable = false)
    private Order order;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id",nullable = false)
    private Product product;

    @NotNull
    @Column(name = "unit_price", precision =  10, scale = 2)
    private BigDecimal unitPrice;
    @NotNull
    private Integer quantity;
    @NotNull
    @Column(name = "total_price", precision =  10, scale = 2)
    private BigDecimal totalPrice;

    public OrderItem(Order order, Product product, Integer quantity) {
        this.order=order;
        this.product=product;
        this.quantity=quantity;
        this.unitPrice=product.getPrice();
        this.totalPrice=unitPrice.multiply(BigDecimal.valueOf(quantity));
    }
}
