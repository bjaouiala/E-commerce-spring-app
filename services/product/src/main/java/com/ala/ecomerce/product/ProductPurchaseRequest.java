package com.ala.ecomerce.product;

import jakarta.validation.constraints.NotNull;

public record ProductPurchaseRequest(
        @NotNull(message = "product is required")
        Integer productId,
        @NotNull(message = "product is required")
        double quantity
) {
}
