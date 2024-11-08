package com.ala.ecommerce.notification;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationProducer {
    private final KafkaTemplate<String,PaymentNotificationRequest> template;

    public  void sendNotification(PaymentNotificationRequest notificationRequest){
        log.info("notification sended correctly {}",notificationRequest);
        Message<PaymentNotificationRequest> message= MessageBuilder.withPayload(notificationRequest)
                        .setHeader(KafkaHeaders.TOPIC,"payment-topic").build();
        template.send(message);


    }
}
