package com.rmnk12k.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "order_products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderProducts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "products_id")
    private Product product;

    public void setOrder(Order order) {
        this.order = order;
        order.getOrderProducts().add(this);
    }

    public void setProduct(Product product) {
        this.product = product;
        product.getOrderProducts().add(this);
    }
}
