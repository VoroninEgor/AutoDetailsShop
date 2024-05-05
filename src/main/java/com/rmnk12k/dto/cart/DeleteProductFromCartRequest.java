package com.rmnk12k.dto.cart;

import jakarta.validation.constraints.NotNull;

public record DeleteProductFromCartRequest(
        @NotNull Long productId
) {
}
