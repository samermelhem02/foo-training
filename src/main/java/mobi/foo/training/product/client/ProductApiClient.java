package mobi.foo.training.product.client;

import lombok.RequiredArgsConstructor;
import mobi.foo.training.FooResponse;
import mobi.foo.training.product.entity.Product;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class ProductApiClient {

    private static final String BASE_URL = "http://localhost:8080";

    private final RestTemplate restTemplate;

    public ResponseEntity<FooResponse> getProducts(String apiVersion) {
        // I can also use getForEntity(BASE_URL,FooResponse.class) which gets the whole entity response
        FooResponse fooResponse = restTemplate.getForObject(BASE_URL + "/products",FooResponse.class);


        return ResponseEntity.ok(fooResponse);
    }

    public ResponseEntity<FooResponse> getProductById(String apiVersion, long id) {
        String apiUrl = BASE_URL + "/product/{id}";
        FooResponse fooResponse = restTemplate.getForObject(apiUrl,FooResponse.class,id);
        return ResponseEntity.ok(fooResponse);
    }

    public ResponseEntity<FooResponse> save(Product product) {
        String apiUrl = BASE_URL + "/product/create";
        FooResponse response = restTemplate.postForObject(apiUrl, product, FooResponse.class);
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<FooResponse> deleteProduct(Long id) {
        String apiUrl = BASE_URL + "/product/delete/{pid}";
        ResponseEntity<FooResponse> responseEntity = restTemplate.exchange(apiUrl, HttpMethod.DELETE, null, FooResponse.class, id);
        return responseEntity;
    }


}



