package com.ala.ecommerce.payment;

import java.math.BigDecimal;

public record PaymentRequest(
        Integer id,
        BigDecimal amount,
        PaymentMethod paymentMethode,
        Integer orderId,
        String orderReference,
        Customer customer
) {
}
