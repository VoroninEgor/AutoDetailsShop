package com.rmnk12k.controller;

import com.rmnk12k.dto.product.ProductCreateRequest;
import com.rmnk12k.dto.product.ProductResponse;
import com.rmnk12k.dto.product.ProductUpdateRequest;
import com.rmnk12k.service.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Tag(name = "products", description = "the products API")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<ProductResponse> getAllProducts() {
        return productService.getAll();
    }

    @GetMapping("/{id}")
    public ProductResponse getProduct(@PathVariable Long id) {
        return productService.get(id);
    }

    @PostMapping
    public void createProduct(@RequestBody @Valid ProductCreateRequest productCreateRequest) {
        productService.create(productCreateRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.delete(id);
    }

    @PutMapping("/{id}")
    public void updateProduct(@PathVariable Long id, @RequestBody @Valid ProductUpdateRequest productUpdateRequest) {
        productService.update(id, productUpdateRequest);
    }
}
