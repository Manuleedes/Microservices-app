package com.Lidigu.ecommerce.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PurchaseRequest(
        @NotNull(message = "product mandatory")
        Integer productId,
        @Positive(message = "quantity mandatory")
        double quantity
) {
}
