package com.rmnk12k.repo;

import com.rmnk12k.entity.OrderProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderProductsRepo extends JpaRepository<OrderProducts, Long> {
}
