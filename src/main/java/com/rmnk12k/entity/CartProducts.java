package com.rmnk12k.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cart_products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartProducts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "carts_id")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "products_id")
    private Product product;

    public void setCart(Cart cart) {
        this.cart = cart;
        cart.getCartProducts().add(this);
    }

    public void setProduct(Product product) {
        this.product = product;
        product.getCartProducts().add(this);
    }
}
