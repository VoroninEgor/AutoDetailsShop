package com.rmnk12k.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cart {
    @Id
    private Long id;

    @OneToMany(mappedBy = "cart", fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @ToString.Exclude
    @Builder.Default
    private List<CartProducts> cartProducts = new ArrayList<>();

    @OneToOne
    @PrimaryKeyJoinColumn
    private User user;
}
