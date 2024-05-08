package com.rmnk12k.dto.user;

import jakarta.validation.constraints.NotNull;

public record UserUpdateRequest(
        @NotNull String name,
        @NotNull String password,
        @NotNull String email
) {
}
