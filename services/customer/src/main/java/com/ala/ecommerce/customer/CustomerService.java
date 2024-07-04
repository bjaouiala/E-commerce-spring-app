package com.ala.ecommerce.customer;

import com.ala.ecommerce.exceptions.CustomerNotfoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository repository;
    private final CustomerMapper mapper ;



    public String createCustomer(CustommerRequest request) {
        var customer = repository.save(mapper.toCustomer(request));
        return customer.getId();
    }

    public void updateCustommer(CustommerRequest request) {
        var customer = repository.findById(request.id())
                .orElseThrow(() -> new CustomerNotfoundException(
                        String.format("cannot update customer :: no customer found with the provided id :: %s",request.id())
                ));
        customer.setFirstName(request.firstName());
        customer.setLastName(request.lastName());
        customer.setEmail(request.email());
        customer.setAddress(request.address());
        repository.save(customer);


    }

    public List<CustommerResponse> findAllCustomers() {
        return repository.findAll()
                .stream()
                .map(mapper::fromCustomer)
                .collect(Collectors.toList());
    }

    public Boolean existById(String id) {
        return repository.findById(id).isPresent();
    }

    public CustommerResponse findById(String customerId) {
        return repository.findById(customerId)
                .map(mapper::fromCustomer)
                .orElseThrow(() -> new CustomerNotfoundException(String.format("No Customer found with the id ::  %s",customerId)));
    }

    public void deleteById(String customerId) {
        repository.deleteById(customerId);
    }
}
