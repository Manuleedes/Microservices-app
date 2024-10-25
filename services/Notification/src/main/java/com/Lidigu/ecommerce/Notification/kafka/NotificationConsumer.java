package com.Lidigu.ecommerce.Notification.kafka;

import com.Lidigu.ecommerce.Notification.email.EmailService;
import com.Lidigu.ecommerce.Notification.kafka.order.OrderConfirmation;
import com.Lidigu.ecommerce.Notification.kafka.payment.PaymentConfirmation;
import com.Lidigu.ecommerce.Notification.notification.Notification;
import com.Lidigu.ecommerce.Notification.notification.NotificationRepository;
import com.Lidigu.ecommerce.Notification.notification.NotificationType;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {
    private final NotificationRepository repository;
     private final EmailService emailService;
    @KafkaListener(topics = "payment-topic")
    public void consumePaymentSuccessNotification(PaymentConfirmation paymentConfirmation) throws MessagingException {
        log.info(String.format("consuming the message from payment-topic Topic:: %s",paymentConfirmation));
        repository.save(
                Notification.builder()
                        .type(NotificationType.PAYMENT_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .paymentConfirmation(paymentConfirmation)
                        .build()
        );
        //send email
        var customerName= paymentConfirmation.customerFirstName()+ " " +paymentConfirmation.customerLastName();
        try {
            emailService.sendPaymentSuccessEmail(
                    paymentConfirmation.customerEmail(),
                    customerName,
                    paymentConfirmation.amount(),
                    paymentConfirmation.orderReference()
            );
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }
        @KafkaListener(topics = "order-topic")
        public void consumeOrderConfirmationNotification(OrderConfirmation orderConfirmation) throws MessagingException {
            log.info(String.format("consuming the message from order-topic Topic:: %s", orderConfirmation));
            repository.save(
                    Notification.builder()
                            .type(NotificationType.ORDER_CONFIRMATION)
                            .notificationDate(LocalDateTime.now())
                            .orderConfirmation(orderConfirmation)
                            .build()
            );
        //send email

            var customerName = orderConfirmation.customer().firstname() + " " + orderConfirmation.customer().lastname();

                    emailService.sendOrderConfirmationEmail(
                            orderConfirmation.customer().email(),
                            customerName,
                            orderConfirmation.totalAmount(),
                            orderConfirmation.OrderReference(),
                            orderConfirmation.products()

                    );

                }
            }
