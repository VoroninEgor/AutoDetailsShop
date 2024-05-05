package com.rmnk12k;

import com.rmnk12k.entity.Cart;
import com.rmnk12k.entity.CartProducts;
import com.rmnk12k.entity.Product;
import com.rmnk12k.repo.CartProductsRepo;
import com.rmnk12k.repo.CartRepo;
import com.rmnk12k.repo.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@SpringBootApplication
@RequiredArgsConstructor
public class AutoDetailsShopApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(AutoDetailsShopApplication.class, args);
    }


    private final CartRepo cartRepo;
    private final ProductRepo productRepo;
    private final CartProductsRepo cartProductsRepo;

    @Override
    public void run(String... args) throws Exception {
//        Cart cart = Cart.builder()
//                .build();
//        Product product = Product.builder()
//                .name("ada")
//                .price(23.0)
//                .type("ada")
//                .description("desc")
//                .updated(Timestamp.valueOf(LocalDateTime.now()))
//                .created(Timestamp.valueOf(LocalDateTime.now()))
//                .build();
//        cartRepo.save(cart);
//        productRepo.save(product);
//


    }
}
