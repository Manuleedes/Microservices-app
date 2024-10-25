package com.Lidigu.ecommerce.Product.product;


import java.math.BigDecimal;

public record ProductResponse(
        Integer id,
        String name,
        String description,

        double availableQuantity,
        BigDecimal price,

        Integer cartegoryId,
        String cartegoryName,
        String cartegoryDescription
) {
}
