package com.ala.ecomerce.customer;

public record Customer(
        String id,
        String firstName,
        String lastName,
        String email
) {
}
