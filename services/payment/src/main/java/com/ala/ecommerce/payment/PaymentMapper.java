package com.ala.ecommerce.payment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
public class PaymentMapper {
    Payment toPayment(PaymentRequest request){
        return Payment.builder()
                .paymentMethode(request.paymentMethode())
                .amount(request.amount())
                .OrderId(request.orderId())
                .id(request.id())
                .build();
    }

}
