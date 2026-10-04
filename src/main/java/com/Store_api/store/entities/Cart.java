package com.Store_api.store.entities;

import com.Store_api.store.entities.CartItem;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(insertable = false, updatable = false)
    private Date dateCreated;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<CartItem> cartItems = new LinkedHashSet<>();

    //The logic is directly related to the state of Cart:
    public BigDecimal getTotalPrice() {
        return cartItems.stream()
                .map(CartItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    //The logic is directly related to the state of Cart:
    public CartItem getItems(Long productId) {
        return getCartItems().stream()
                .filter(c -> c.getProduct().getId().equals(productId))
                .findFirst()
                .orElse(null);
    }
    public CartItem addItem(Product product)
    {
        var cartItem=getItems(product.getId());
        if (cartItem != null)
            cartItem.setQuantity(cartItem.getQuantity() + 1);
        else {
            cartItem = new CartItem();
            cartItem.setQuantity(1);
            cartItem.setProduct(product);
            cartItem.setCart(this);
            cartItems.add(cartItem); // cartItem Can not exist without Cart
        }
        return cartItem;
    }
    public void removeItem(Long  productId) {
        var cartItem = getItems(productId);
        if (cartItem != null) {
            cartItems.remove(cartItem);
            cartItem.setCart(null);
        }
    }
    public void clear()
    {
        cartItems.clear();
    }
}