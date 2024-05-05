package com.rmnk12k.dto.product;

import jakarta.validation.constraints.NotNull;

public record ProductUpdateRequest(
        @NotNull String name,
        @NotNull Double price,
        @NotNull String description,
        @NotNull String type
) {
}
