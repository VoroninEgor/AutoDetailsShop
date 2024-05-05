package com.rmnk12k.dto.cart;

import jakarta.validation.constraints.NotNull;

public record AddProductToCartRequest(
        @NotNull Long productId
) {
}
