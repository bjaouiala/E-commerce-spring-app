package com.ala.ecommerce.notification;

import com.ala.ecommerce.payment.PaymentMethod;

import java.math.BigDecimal;

public record PaymentNotificationRequest(
        String orderReference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerName,
        String customerLastName,
        String customerEmail
) {
}
