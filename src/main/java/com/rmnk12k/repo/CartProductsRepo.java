package com.rmnk12k.repo;

import com.rmnk12k.entity.CartProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CartProductsRepo extends JpaRepository<CartProducts, Long> {
    @Query("DELETE FROM CartProducts t where t.cart.id = :cartId and t.product.id = :productId")
    @Modifying
    void deleteByCartIdAndProductId(Long cartId, Long productId);

    @Query("DELETE FROM CartProducts t where t.cart.id = :cartId")
    @Modifying
    void deleteAllProduct(Long cartId);
}
