package com.Lidigu.ecommerce.Product.product;

import jakarta.validation.constraints.NotNull;

public record ProductPurchaseRequest(
        @NotNull(message = "product must abide")
        Integer productId,
        @NotNull(message = "quantity must abide")
        double quantity
) {
}
