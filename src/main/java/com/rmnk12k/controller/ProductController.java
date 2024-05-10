package com.rmnk12k.controller;

import com.rmnk12k.dto.product.ProductCreateRequest;
import com.rmnk12k.dto.product.ProductResponse;
import com.rmnk12k.dto.product.ProductUpdateRequest;
import com.rmnk12k.service.ProductService;
import com.rmnk12k.utill.ProductType;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Tag(name = "products", description = "the products API")
public class ProductController {

    private final ProductService productService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public List<ProductResponse> getAllProducts() {
        return productService.getAll();
    }

    @GetMapping("/filter")
    public List<ProductResponse> getAllProductsFilteredByType(@RequestParam @NotNull String type) {
        return productService.getAllByType(type);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{id}")
    public ProductResponse getProduct(@PathVariable Long id) {
        return productService.get(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public void createProduct(@RequestBody @Valid ProductCreateRequest productCreateRequest) {
        productService.create(productCreateRequest);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.delete(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public void updateProduct(@PathVariable Long id, @RequestBody @Valid ProductUpdateRequest productUpdateRequest) {
        productService.update(id, productUpdateRequest);
    }
}
