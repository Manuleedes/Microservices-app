package com.Lidigu.ecommerce.payment;

import com.Lidigu.ecommerce.customer.CustomerResponse;
import com.Lidigu.ecommerce.order.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer
) {
}
