package com.Lidigu.ecommerce.Notification.kafka.order;

public record Customer(

        String id,

        String firstname,
        String lastname,
        String email
) {
}
