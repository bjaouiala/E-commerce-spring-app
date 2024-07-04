package com.ala.ecommerce.customer;

import jakarta.validation.constraints.NotNull;

public record CustommerRequest(
         String id,
         @NotNull(message = "customer first name is required")
         String firstName,
         @NotNull(message = "customer last name is required")
         String lastName,
         @NotNull(message = "customer email is required")
         String email,
         @NotNull(message = "customer address is required")
         Address address
) {
}
