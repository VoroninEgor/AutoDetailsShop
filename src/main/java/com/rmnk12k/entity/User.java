package com.rmnk12k.entity;

import jakarta.persistence.*;
import jakarta.websocket.OnError;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String password;

    private String email;

    private String role;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Timestamp created;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Timestamp updated;

    @OneToOne(mappedBy = "user")
    private Cart cart;

    @OneToMany(mappedBy = "user")
    private List<Order> orders;
}
