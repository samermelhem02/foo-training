package mobi.foo.training.product.controller;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mobi.foo.training.FooResponse;
import mobi.foo.training.product.dto.ProductDTO;
import mobi.foo.training.product.entity.Product;
import mobi.foo.training.product.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


@RestController
@RequiredArgsConstructor
@Validated
public class ProductController {

    private final ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<FooResponse> findAll() {
        List<ProductDTO> productDTOList;

        productDTOList = productService.findAll();
        FooResponse response = FooResponse.builder().data(productDTOList).message("Got all the products").stats(true).build();
        return new ResponseEntity<>(response, HttpStatus.OK);

    }


    @GetMapping("product/{id}")
    public ResponseEntity<FooResponse> findById(@PathVariable long id)
    {
        ProductDTO productDTO;
        productDTO = productService.findById(id);
        FooResponse response = FooResponse.builder().data(productDTO).message("Got a product by Id").stats(true).build();
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @PostMapping("product/create")
    public ResponseEntity<FooResponse> save(@Valid @RequestBody Product product)
    {
        productService.save(product);
        System.out.println("Saving a product");
        ProductDTO productDTO = new ProductDTO(product.getPid(),product.getPname());
        FooResponse response = FooResponse.builder().data(productDTO).message("Created/Updated a new Product of id " + product.getPid() + " and name = " + product.getPname()).stats(true).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @DeleteMapping("product/delete/{pid}")
    public ResponseEntity<FooResponse> delete(@PathVariable long pid)
    {
        ProductDTO productDTO = productService.findById(pid);
        productService.delete(pid);

        FooResponse response = FooResponse.builder().data(productDTO).message("Deleted a Product of id " + pid).stats(true).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/async-task")
    public ResponseEntity<FooResponse> performAsyncTask() throws ExecutionException, InterruptedException {
        CompletableFuture<String> msg =  productService.AsyncTask();
        System.out.println("This instruction is getting executed and not waiting for the above called function");

        //now im gonna wait till the async function returns the msg to build my response
        String message = msg.get();
        FooResponse response = FooResponse.builder().message(message).stats(true).build();

        return new ResponseEntity<>(response,HttpStatus.OK);
    }

}
