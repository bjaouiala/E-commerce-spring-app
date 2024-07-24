package com.ala.ecomerce.product;

import com.ala.ecomerce.exception.ProductPurchaseException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository repository;
    private final ProductMapper mapper;

    public Integer createProduct(ProductRequest request) {
        var product = mapper.toProduct(request);
        return repository.save(product).getId();

    }

    public List<ProductPurchaseResponse> purchaseProduct(List<ProductPurchaseRequest> requests) {
        var productId = requests
                .stream()
                .map(ProductPurchaseRequest::productId)
                .toList();
        var storedProduct = repository.findAllByIdInOrderById(productId);
        if (productId.size() != storedProduct.size()){
            throw new ProductPurchaseException("one or more product not available");
        }
        var storedRequest = requests.stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::productId))
                .toList();
        var purchaseProducts = new ArrayList<ProductPurchaseResponse>();
        for (int i = 0; i < storedProduct.size() ; i++){
            var product = storedProduct.get(i);
            var productRequest = storedRequest.get(i);
            if (product.getAvailableQuantity() < productRequest.quantity()){
                throw new ProductPurchaseException("insufficient stock quaantity for product with id "+ productRequest.productId());
            }
            var newAvailableQuantity = product.getAvailableQuantity() - productRequest.quantity();
            product.setAvailableQuantity(newAvailableQuantity);
            repository.save(product);
            purchaseProducts.add(mapper.toProductPurchaseResponse(product, productRequest.quantity()));
    }
        return purchaseProducts;
    }

    public ProductResponse findById(Integer productId) {
        return repository.findById(productId)
                .map(mapper::toProductResponse)
                .orElseThrow(()-> new EntityNotFoundException("product Not Found exception"+ productId));
    }

    public List<ProductResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toProductResponse)
                .collect(Collectors.toList());
    }
}
