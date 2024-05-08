package com.rmnk12k.dto.user;

import jakarta.validation.constraints.NotNull;

public record LoginUserRequest(
        @NotNull String email,
        @NotNull String password
) {
}
