package com.rmnk12k.dto.user;

import lombok.Builder;

import java.sql.Timestamp;

@Builder
public record UserResponse(
        Long id,
        String name,
        String email,
        String role,
        Timestamp created,
        Timestamp updated
) {
}
