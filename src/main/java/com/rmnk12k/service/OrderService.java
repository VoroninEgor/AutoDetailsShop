package com.rmnk12k.service;

import com.rmnk12k.dto.order.CreateOrderRequest;
import com.rmnk12k.dto.order.ListOrderResponse;
import com.rmnk12k.dto.order.OrderResponse;
import com.rmnk12k.dto.order.UpdateOrderStatusRequest;
import com.rmnk12k.dto.product.ProductResponse;
import com.rmnk12k.entity.*;
import com.rmnk12k.exception.CartNotFoundException;
import com.rmnk12k.exception.OrderNotFoundException;
import com.rmnk12k.exception.UserNotFoundException;
import com.rmnk12k.repo.*;
import com.rmnk12k.utill.OrderStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final OrderRepo orderRepo;
    private final CartRepo cartRepo;
    private final CartProductsRepo cartProductsRepo;
    private final OrderProductsRepo orderProductsRepo;
    private final UserRepo userRepo;

    public OrderResponse get(Long id) {
        log.info("get order with id: {}", id);

        Order order = orderRepo.findById(id).orElseThrow(OrderNotFoundException::new);
        return mapOrderToOrderResponse(order);
    }

    @Transactional
    public void create(CreateOrderRequest request) {
        log.info("create order: {}", request);

        Cart cart = cartRepo.findById(request.userId()).orElseThrow(CartNotFoundException::new);
        List<Product> products = cart.getCartProducts().stream().map(CartProducts::getProduct).toList();
        cartProductsRepo.deleteAllProduct(request.userId());
        Double total = products.stream().map(Product::getPrice).reduce((double) 0, Double::sum);

        Order order = Order.builder()
                .user(User.builder().id(request.userId()).build())
                .total(total)
                .deliveryAddress(request.deliveryAddress())
                .paymentMethod(request.paymentMethod())
                .status(OrderStatus.PROCESSING.toString())
                .build();
        orderRepo.save(order);

        for (Product product: products) {
            OrderProducts orderProducts = new OrderProducts();
            orderProducts.setOrder(order);
            orderProducts.setProduct(product);
            orderProductsRepo.save(orderProducts);
        }
    }

    public ListOrderResponse getUsersOrders(Long userId) {
        log.info("get orders by user id: {}", userId);

        User user = userRepo.findById(userId).orElseThrow(UserNotFoundException::new);
        List<Order> orders = user.getOrders();
        return new ListOrderResponse(
            orders.stream().map(this::mapOrderToOrderResponse).toList()
        );
    }

    public void updateStatus(UpdateOrderStatusRequest request) {
        log.info("update order status with id: {}", request.id());

        Order order = orderRepo.findById(request.id()).orElseThrow(OrderNotFoundException::new);
        order.setStatus(request.status());
        orderRepo.save(order);
    }

    private OrderResponse mapOrderToOrderResponse(Order order) {
        List<ProductResponse> products = order.getOrderProducts().stream()
                .map(OrderProducts::getProduct)
                .map(product -> ProductResponse.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .price(product.getPrice())
                        .description(product.getDescription())
                        .type(product.getType())
                        .created(product.getCreated())
                        .updated(product.getUpdated())
                        .build())
                .toList();
        Double total = products.stream().map(ProductResponse::price).reduce((double) 0, Double::sum);
        return OrderResponse.builder()
                .orderId(order.getId())
                .userId(order.getUser().getId())
                .deliveryAddress(order.getDeliveryAddress())
                .paymentMethod(order.getPaymentMethod())
                .total(total)
                .products(products)
                .build();
    }
}
