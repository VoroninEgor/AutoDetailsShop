package com.rmnk12k.dto.order;

import jakarta.validation.constraints.NotNull;

public record CreateOrderRequest(
        @NotNull Long userId,
        @NotNull String deliveryAddress,
        @NotNull String paymentMethod
) {
}
