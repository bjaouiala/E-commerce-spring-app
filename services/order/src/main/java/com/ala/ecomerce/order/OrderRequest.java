package com.ala.ecomerce.order;

import com.ala.ecomerce.product.PurchaseRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequest(

        Integer id,
        String reference,
        @Positive(message = "order amount should be positive")
        BigDecimal amount,
        @NotNull(message = "payment should be precised")
        PaymentMethod paymentMethod,

        @NotNull(message = "customer should be p0resent")
        @NotBlank
        @NotEmpty
        String customerId,
        @NotEmpty(message = "you should at least purchase one product")
        List<PurchaseRequest> products


) {
}
