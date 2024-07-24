package com.ala.ecomerce.product;

import java.math.BigDecimal;

public record PurchaseResponse(
        Integer productId,
        String name,
        BigDecimal price,
        double quantity
) {
}
