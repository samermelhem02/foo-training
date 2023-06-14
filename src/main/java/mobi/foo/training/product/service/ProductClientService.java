package mobi.foo.training.product.service;

import lombok.RequiredArgsConstructor;
import mobi.foo.training.FooResponse;
import mobi.foo.training.product.client.ProductApiClient;
import mobi.foo.training.product.entity.Product;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductClientService {

    private final ProductApiClient productApiClient;

    public ResponseEntity<FooResponse> getProducts(String apiVersion) {
        return productApiClient.getProducts(apiVersion);
    }
    public ResponseEntity<FooResponse> getProductById(String apiVersion, Long id) {
        return productApiClient.getProductById(apiVersion,id);
    }

    public ResponseEntity<FooResponse> save(Product product) {
        return productApiClient.save(product);
    }

    public ResponseEntity<FooResponse> deleteProduct(Long id) {
        return productApiClient.deleteProduct(id);
    }

}
