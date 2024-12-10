package com.Lidigu.ecommerce.orderLine;

public record OrderLineRequest(
        Integer Id,
        Integer orderId,
        Integer productId,
        double quantity
) {
}
