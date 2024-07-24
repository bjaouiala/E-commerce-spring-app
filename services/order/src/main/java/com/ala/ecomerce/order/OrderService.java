package com.ala.ecomerce.order;

import com.ala.ecomerce.customer.CustomerClient;
import com.ala.ecomerce.exception.BusinessException;
import com.ala.ecomerce.kafka.OrderConfirmation;
import com.ala.ecomerce.kafka.OrderProducer;
import com.ala.ecomerce.orderLine.OrderLine;
import com.ala.ecomerce.orderLine.OrderLineRequest;
import com.ala.ecomerce.orderLine.OrderLineService;
import com.ala.ecomerce.product.ProductClient;
import com.ala.ecomerce.product.PurchaseRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderRepository repository;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;


    public Integer createOrder(OrderRequest request) {
        var customer = this.customerClient.findCustomerById(request.customerId())
                .orElseThrow(() -> new BusinessException("cannot create order:: No Customer exists with the provided ID"));
        var purchasedProduct = this.productClient.purchaseResponses(request.products());
        var order = this.repository.save(mapper.toOrder(request));
        for (PurchaseRequest purchaseRequest : request.products()){
            orderLineService.saveOrderLine(new OrderLineRequest(
                    null,
                    order.getId(),
                    purchaseRequest.productId(),
                    purchaseRequest.quantity()
            ));

        }
        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(request.reference(),
                        request.amount(),
                        request.paymentMethod(),
                        customer,
                        purchasedProduct
                )
        );
        return order.getId();

    }

    public List<OrderResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::fromOrder)
                .collect(Collectors.toList());
    }

    public OrderResponse findById(Integer orderId) {
        return repository.findById(orderId).map(mapper::fromOrder)
                .orElseThrow(() -> new EntityNotFoundException(String.format("No order founded with the provided id %s",orderId)));
    }
}
