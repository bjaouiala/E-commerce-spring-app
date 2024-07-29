package com.ala.ecommerce.payment;

import com.ala.ecommerce.notification.NotificationProducer;
import com.ala.ecommerce.notification.PaymentNotificationRequest;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService {
    private final PaymentRepository repository;
    private final PaymentMapper mapper;
    private final NotificationProducer notificationProducer;
    public Integer savePayment(PaymentRequest request) {
        var payment = repository.save(mapper.toPayment(request));
        notificationProducer.sendNotification(new PaymentNotificationRequest(
                request.orderReference(),
                request.amount(),
                request.paymentMethode(),
                request.customer().firstName(),
                request.customer().lastName(),
                request.customer().email()
        ));
        log.info(request.customer().firstName());
        log.info(request.customer().email());

      return payment.getId();
    }
}
