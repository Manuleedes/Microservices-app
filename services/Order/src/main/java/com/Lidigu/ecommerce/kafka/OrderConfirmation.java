package com.Lidigu.ecommerce.kafka;

import com.Lidigu.ecommerce.customer.CustomerResponse;
import com.Lidigu.ecommerce.order.PaymentMethod;
import com.Lidigu.ecommerce.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products
) {
}
