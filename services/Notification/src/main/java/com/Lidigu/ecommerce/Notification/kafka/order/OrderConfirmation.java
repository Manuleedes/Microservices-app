package com.Lidigu.ecommerce.Notification.kafka.order;

import java.math.BigDecimal;
import com.Lidigu.ecommerce.Notification.kafka.payment.PaymentMethod;
import java.util.List;

public record OrderConfirmation(
        String OrderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        Customer customer,
        List<Product> products

) {
}
