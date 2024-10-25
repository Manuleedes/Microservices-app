package com.Lidigu.ecommerce.Product.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductRequest(

         Integer id,
         @NotNull(message = "product name required")
         String name,
         @NotNull(message = "product description required")
         String description,
         @Positive(message = "available quantity should be positive")
         double availableQuantity,
         @Positive(message = " price should be positive")
         BigDecimal price,
         @NotNull(message = "product cartegory required")
        Integer cartegoryId
) {
}
