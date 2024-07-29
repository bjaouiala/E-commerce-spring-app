package com.ala.ecommerce.kafka;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String reference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Customer customer,
        List<Product> products

        ) {

}
