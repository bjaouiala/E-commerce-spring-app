package com.ala.ecomerce.orderLine;

public record OrderLineRequest(Integer id,
                               Integer orderId,
                               Integer productId,
                               double quantity) {
}
