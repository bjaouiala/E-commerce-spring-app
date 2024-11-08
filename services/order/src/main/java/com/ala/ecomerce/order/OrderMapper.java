package com.ala.ecomerce.order;

import org.springframework.stereotype.Service;

@Service
public class OrderMapper {
    public Order toOrder(OrderRequest request) {
        return Order.builder()
                .id(request.id())
                .customerId(request.customerId())
                .amount(request.amount())
                .reference(request.reference())
                .paymentMethod(request.paymentMethod())
                .build();
    }

    public OrderResponse fromOrder(Order order) {
        return new OrderResponse(order.getId(),
                order.getReference(),
                order.getAmount(),
                order.getPaymentMethod(),
                order.getCustomerId());
    }
}
