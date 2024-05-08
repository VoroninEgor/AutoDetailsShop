package com.rmnk12k.dto.auth;

public record LoginResponse(
        String token,
        long expiresIn
) {
}
