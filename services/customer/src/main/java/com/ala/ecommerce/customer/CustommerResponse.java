package com.ala.ecommerce.customer;

import jakarta.validation.constraints.NotNull;

public record CustommerResponse(
        String id,
        String firstName,
        String lastName,
        String email,
        Address address
) {
}
