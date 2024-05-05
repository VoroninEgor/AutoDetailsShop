package com.rmnk12k.dto.product;

import lombok.Builder;

import java.sql.Timestamp;

@Builder
public record ProductResponse(
        Long id,
        String name,
        Double price,
        String description,
        String type,
        Timestamp created,
        Timestamp updated
) {
}
