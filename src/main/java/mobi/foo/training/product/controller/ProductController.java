package mobi.foo.training.product.controller;

import mobi.foo.training.FooResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    
    @PostMapping("/Api/v1/print")
    public  ResponseEntity<FooResponse> printHello() {

        FooResponse response = FooResponse.builder().data("success").message("Hello World!").stats(true).build();
        return new ResponseEntity<>(response,HttpStatus.OK);
        
    }
    


}
