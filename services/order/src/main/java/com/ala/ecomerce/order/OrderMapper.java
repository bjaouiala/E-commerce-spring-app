package com.ala.ecomerce.order;

import org.springframework.stereotype.Service;

@Service
public class OrderMapper {
    public Order toOrder(OrderRequest request) {
        return Order.builder()
                .id(request.id())
                .customerId(request.customerId())
                .totalAmount(request.amount())
                .referece(request.reference())
                .paymentMethod(request.paymentMethod())
                .build();
    }

    public OrderResponse fromOrder(Order order) {
        return new OrderResponse(order.getId(),
                order.getReferece(),
                order.getTotalAmount(),
                order.getPaymentMethod(),
                order.getCustomerId());
    }
}
