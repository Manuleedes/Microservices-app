package com.Lidigu.ecommerce.Payment.payment;

import java.math.BigDecimal;

public record PaymentRequest(
         Integer id,
         BigDecimal amount,
         PaymentMethod paymentMethod,
         Integer orderId,
         String orderReference,
         Customer customer
) {
}
