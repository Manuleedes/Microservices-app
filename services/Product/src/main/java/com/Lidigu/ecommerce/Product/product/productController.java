package com.Lidigu.ecommerce.Product.product;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class productController {
    private final ProductService service;
    @PostMapping
    public ResponseEntity<Integer> createProduct(
           @RequestBody @Valid ProductRequest request
    ){
       return ResponseEntity.ok(service.createProduct(request));
    }
    @PostMapping("/purchase")
    public  ResponseEntity<List<ProductPurchaseResponse>> purchaseProducts(
            @RequestBody List<ProductPurchaseRequest> request
            ){
        return ResponseEntity.ok(service.purchaseProducts(request));
    }
    @GetMapping("{product-id}")
    public ResponseEntity<ProductResponse> findById(
            @PathVariable("product-id") Integer productId

    ){
        return ResponseEntity.ok(service.findById(productId));
    }
    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAll(

    ){
        return ResponseEntity.ok(service.findAll());
    }
}
