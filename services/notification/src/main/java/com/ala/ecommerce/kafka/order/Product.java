package com.ala.ecommerce.kafka.order;

import java.math.BigDecimal;

public record Product(
        Integer productId,
        String name,
        String description,
        BigDecimal amount,
        double quantity

) {
}
