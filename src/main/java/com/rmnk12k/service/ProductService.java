package com.rmnk12k.service;

import com.rmnk12k.dto.product.ProductCreateRequest;
import com.rmnk12k.dto.product.ProductResponse;
import com.rmnk12k.dto.product.ProductUpdateRequest;
import com.rmnk12k.entity.Product;
import com.rmnk12k.exception.ProductNotFoundException;
import com.rmnk12k.repo.ProductRepo;
import com.rmnk12k.utill.ProductType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepo productRepo;

    public void create(ProductCreateRequest productCreateRequest) {
        log.info("create product: {}", productCreateRequest);

        ProductType type = ProductType.getType(productCreateRequest.type());
        Timestamp currentTime = Timestamp.valueOf(LocalDateTime.now());
        Product product = Product.builder()
                .name(productCreateRequest.name())
                .price(productCreateRequest.price())
                .description(productCreateRequest.description())
                .type(type.toString())
                .created(currentTime)
                .updated(currentTime)
                .build();
        productRepo.save(product);
    }

    public void delete(Long id) {
        log.info("delete product with id: {}", id);
        productRepo.deleteById(id);
    }


    public void update(Long id, ProductUpdateRequest productUpdateRequest) {
        log.info("update product with id: {}, new product: {}", id, productUpdateRequest);

        Optional<Product> optionalProduct = productRepo.findById(id);
        if (optionalProduct.isPresent()) {
            Product oldProduct = optionalProduct.get();
            ProductType type = ProductType.getType(productUpdateRequest.type());
            Product product = Product.builder()
                    .name(productUpdateRequest.name())
                    .price(productUpdateRequest.price())
                    .description(productUpdateRequest.description())
                    .type(type.toString())
                    .created(oldProduct.getCreated())
                    .updated(Timestamp.valueOf(LocalDateTime.now()))
                    .build();
            productRepo.save(product);
        }
    }

    public ProductResponse get(Long id) {
        log.info("get product with id: {}", id);

        Product product = productRepo.findById(id).orElseThrow(ProductNotFoundException::new);
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .description(product.getDescription())
                .type(product.getType())
                .created(product.getCreated())
                .updated(product.getUpdated())
                .build();
    }

    public List<ProductResponse> getAll() {
        log.info("get all product");

        return productRepo.findAll().stream()
                .map(
                product -> ProductResponse.builder()
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
}
