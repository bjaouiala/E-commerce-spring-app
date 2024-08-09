package com.ala.ecomerce.payment;

import com.ala.ecomerce.customer.Customer;
import com.ala.ecomerce.order.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
                             BigDecimal amount,
                             PaymentMethod paymentMethode,
                             Integer orderId,
                             String orderReference,
                             Customer customer) {
}
