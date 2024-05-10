package com.rmnk12k.dto.order;

import jakarta.validation.constraints.NotNull;

public record UpdateOrderStatusRequest(
        @NotNull Long id,
        @NotNull String status
) {
}
