package com.rmnk12k.dto.order;

import java.util.List;

public record ListOrderResponse(
        List<OrderResponse> orders
) {
}
