package com.Lidigu.ecommerce.Notification.notification;

import com.Lidigu.ecommerce.Notification.kafka.payment.PaymentConfirmation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationRepository extends MongoRepository<Notification, String> {
}
