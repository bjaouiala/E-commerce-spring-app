package com.ala.ecomerce.kafka;

import com.ala.ecomerce.customer.CustomerResponse;
import com.ala.ecomerce.order.PaymentMethod;
import com.ala.ecomerce.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customerResponse,
        List<PurchaseResponse> purchaseResponses
) {
}
