package com.ala.ecommerce.customer;

import com.ala.ecommerce.CustomerApplication;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService service;

    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestBody @Valid CustommerRequest request){
        return ResponseEntity.ok(service.createCustomer(request));
    }

    @PutMapping
    public ResponseEntity<String> updateCustomer(@RequestBody @Valid CustommerRequest request){
        service.updateCustommer(request);
        return ResponseEntity.ok().build();
    }
    @GetMapping
    public ResponseEntity<List<CustommerResponse>> findAll(){
        return ResponseEntity.ok(service.findAllCustomers());
    }

    @GetMapping("/exist/{customer-id}")
    public ResponseEntity<Boolean> existById(@PathVariable("customer-id")
                                             String customerId){
        return ResponseEntity.ok(service.existById(customerId));
    }

    @GetMapping("/{customer-id}")
    public ResponseEntity<CustommerResponse> findById(@PathVariable("customer-id")
                                                      String customerId){
        return ResponseEntity.ok(service.findById(customerId));
    }

    @DeleteMapping("/{customer-id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("customer-id")
                                               String customerId){
        service.deleteById(customerId);
        return ResponseEntity.ok().build();
    }

}
