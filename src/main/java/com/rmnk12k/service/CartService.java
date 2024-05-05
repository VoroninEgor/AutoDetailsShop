package com.rmnk12k.service;

import com.rmnk12k.dto.cart.AddProductToCartRequest;
import com.rmnk12k.dto.cart.DeleteProductFromCartRequest;
import com.rmnk12k.dto.product.ProductResponse;
import com.rmnk12k.entity.Cart;
import com.rmnk12k.entity.CartProducts;
import com.rmnk12k.entity.Product;
import com.rmnk12k.exception.CartNotFoundException;
import com.rmnk12k.exception.ProductNotFoundException;
import com.rmnk12k.repo.CartProductsRepo;
import com.rmnk12k.repo.CartRepo;
import com.rmnk12k.repo.ProductRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartService {
    private final CartRepo cartRepo;
    private final ProductRepo productRepo;
    private final CartProductsRepo cartProductsRepo;

    public List<ProductResponse> getAllProducts(Long cartId) {
        log.info("get all products from cart with id: {}", cartId);

        Cart cart = cartRepo.findById(cartId).orElseThrow(CartNotFoundException::new);
        return cart.getCartProducts().stream()
                .map(CartProducts::getProduct)
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
    }

    public void addProduct(Long cartId, AddProductToCartRequest request) {
        log.info("add product with id: {} intor cart with id: {}", request.productId(), cartId);

        Cart cart = cartRepo.findById(cartId).orElseThrow(CartNotFoundException::new);
        Product product = productRepo.findById(request.productId()).orElseThrow(ProductNotFoundException::new);
        CartProducts cartProducts = new CartProducts();
        cartProducts.setCart(cart);
        cartProducts.setProduct(product);
        cartProductsRepo.save(cartProducts);
    }

    @Transactional
    public void deleteProduct(Long cartId, DeleteProductFromCartRequest request) {
        log.info("delete product with id: {}, from cart with id: {}", request.productId(), cartId);
        cartProductsRepo.deleteByCartIdAndProductId(cartId, request.productId());
    }
}
