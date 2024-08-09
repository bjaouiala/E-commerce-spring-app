package com.ala.ecomerce.kafka;

import com.ala.ecomerce.customer.Customer;
import com.ala.ecomerce.order.PaymentMethod;
import com.ala.ecomerce.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String reference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Customer customer,
        List<PurchaseResponse> products
) {
}
