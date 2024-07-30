package com.ala.ecommerce.kafka;

import com.ala.ecommerce.email.EmailService;
import com.ala.ecommerce.kafka.order.OrderConfirmation;
import com.ala.ecommerce.kafka.payment.PaymentConfirmation;
import com.ala.ecommerce.notification.Notification;
import com.ala.ecommerce.notification.NotificationRepository;
import com.ala.ecommerce.notification.NotificationType;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.ala.ecommerce.notification.NotificationType.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {
    private final NotificationRepository repository;
    private final EmailService emailService;
    @KafkaListener(topics = "payment-topic")
    public void consumePaymentNotification(PaymentConfirmation paymentConfirmation) throws MessagingException {

        log.info(String.format("consuming the message from the payment topic %s ::",paymentConfirmation));
        repository.save(Notification.builder()
                        .notificationType(PAYMENT_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .paymentConfirmation(paymentConfirmation)
                .build());
        var customerName =  paymentConfirmation.customerName()+" "+paymentConfirmation.customerLastName();
        emailService.sendPaymentSuccessEmail(paymentConfirmation.customerEmail(),
               customerName,
                paymentConfirmation.amount(),
                paymentConfirmation.orderReference());


    }

    @KafkaListener(topics = "order-topic")
    public void consumeOrderNotification(OrderConfirmation orderConfirmation) throws MessagingException {
        log.info(String.format("consuming the message from the order topic %s ::",orderConfirmation));
        log.info(orderConfirmation.customer().email());
        repository.save(Notification.builder()
                .notificationType(ORDER_CONFIRMATION)
                .notificationDate(LocalDateTime.now())
                .orderConfirmation(orderConfirmation)
                .build());
        var customerName =  orderConfirmation.customer().firstName()+" "+orderConfirmation.customer().lastName();
        emailService.sendOrderConfirmationEmail(orderConfirmation.customer().email(),
                customerName,
                orderConfirmation.amount(),
                orderConfirmation.reference(),
                orderConfirmation.products());


    }
}
