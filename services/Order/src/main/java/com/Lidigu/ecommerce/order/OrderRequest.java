package com.Lidigu.ecommerce.order;

import com.Lidigu.ecommerce.product.PurchaseRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequest(
        Integer id,
        String reference,
        @Positive(message = "order amount should be positive")
        BigDecimal amount,
        @NotNull(message = "precised")
        PaymentMethod paymentMethod,
        @NotNull(message = "present customer")
        @NotEmpty(message = "present customer")
        @NotBlank(message = "present customer")
        String customerId,
        @NotEmpty(message = "atleast one product")
        List<PurchaseRequest> products
) {
}
