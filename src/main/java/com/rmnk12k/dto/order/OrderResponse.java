package com.rmnk12k.dto.order;

import com.rmnk12k.dto.product.ProductResponse;
import lombok.Builder;

import java.util.List;

@Builder
public record OrderResponse(
        Long orderId,
        Long userId,
        String deliveryAddress,
        String paymentMethod,
        Double total,
        List<ProductResponse> products
) {
}
