package com.Lidigu.ecommerce.Payment.payment;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService service;

    @PostMapping
    public ResponseEntity<Integer> createPayment(
            @RequestBody @Valid PaymentRequest request
    ){
    return ResponseEntity.ok(service.createPayment(request));

}
}
