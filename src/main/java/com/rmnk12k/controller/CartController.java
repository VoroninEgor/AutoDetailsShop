package com.rmnk12k.controller;

import com.rmnk12k.dto.cart.AddProductToCartRequest;
import com.rmnk12k.dto.cart.DeleteProductFromCartRequest;
import com.rmnk12k.dto.product.ProductResponse;
import com.rmnk12k.service.CartService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
@Tag(name = "carts", description = "the carts API")
public class CartController {
    private final CartService cartService;

    @GetMapping("/{id}")
    public List<ProductResponse> getAllProduct(@PathVariable Long id) {
        return cartService.getAllProducts(id);
    }

    @PostMapping("/{id}")
    public void addProduct(@PathVariable Long id, @RequestBody @Valid AddProductToCartRequest request) {
        cartService.addProduct(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id, @RequestBody @Valid DeleteProductFromCartRequest request) {
        cartService.deleteProduct(id, request);
    }
}
