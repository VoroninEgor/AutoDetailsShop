package com.rmnk12k.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double price;

    private String description;

    private String type;

    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp created;

    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp updated;

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @ToString.Exclude
    @Builder.Default
    private List<CartProducts> cartProducts = new ArrayList<>();

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @ToString.Exclude
    @Builder.Default
    private List<OrderProducts> orderProducts = new ArrayList<>();
}
