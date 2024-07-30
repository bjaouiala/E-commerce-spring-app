package com.ala.ecommerce.email;

import lombok.Getter;

public enum EmailTemplate {
    PAYMENT_CONFIRMATION("payment-confirmation.html","payment successfully processed"),
    ORDER_CONFIRMATION("order-confirmation.html","order successfully processed");

    @Getter
    private final String template;
    @Getter
    private final String subject;

    EmailTemplate(String template, String subject) {
        this.template = template;
        this.subject = subject;
    }
}
