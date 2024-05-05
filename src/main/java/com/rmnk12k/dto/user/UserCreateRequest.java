package com.rmnk12k.dto.user;

import jakarta.validation.constraints.NotNull;

public record UserCreateRequest(
        @NotNull String name,
        @NotNull String password,
        @NotNull String email,
        @NotNull String role
) {
}
