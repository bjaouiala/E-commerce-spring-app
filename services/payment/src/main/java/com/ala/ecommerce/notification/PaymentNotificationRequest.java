package com.ala.ecommerce.notification;

import com.ala.ecommerce.payment.PaymentMethode;
import org.apache.kafka.common.protocol.types.Field;

import java.math.BigDecimal;

public record PaymentNotificationTemplate(
        String orderReference,
        BigDecimal amount,
        PaymentMethode paymentMethode,
        String customerName,
        String customerLastName,
        String customerEmail
) {
}
