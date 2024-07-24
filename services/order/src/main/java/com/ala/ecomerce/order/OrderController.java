package com.ala.ecomerce.order;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService service;

    @PostMapping
    public ResponseEntity<Integer> createOrder(@RequestBody @Valid
                                               OrderRequest request){
        return ResponseEntity.ok(service.createOrder(request));
    }
    @GetMapping
    public ResponseEntity<List<OrderResponse>> finall(){
        return ResponseEntity.ok(service.findAll());
    }
    @GetMapping("/{order_id}")
    public ResponseEntity<OrderResponse> findById(@PathVariable Integer order_id){
        return ResponseEntity.ok(service.findById(order_id));

    }
}
