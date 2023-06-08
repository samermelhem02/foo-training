package mobi.foo.training.product.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Product Controller", description = "API endpoints for products")
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "Get all products")
    @GetMapping("/products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Products retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request payload"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<FooResponse> findAll() {
        List<ProductDTO> productDTOList;

        productDTOList = productService.findAll();
        FooResponse response = FooResponse.builder().data(productDTOList).message("Got all the products").stats(true).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Get a product by ID")
    @GetMapping("product/{id}")
    public ResponseEntity<FooResponse> findById(@PathVariable long id) {
        ProductDTO productDTO;
        productDTO = productService.findById(id);
        FooResponse response = FooResponse.builder().data(productDTO).message("Got a product by Id").stats(true).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Create or update a product")
    @PostMapping("product/create")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product created/updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request payload"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<FooResponse> save(@Valid @RequestBody Product product) {
        productService.save(product);
        System.out.println("Saving a product");
        ProductDTO productDTO = new ProductDTO(product.getPid(), product.getPname());
        FooResponse response = FooResponse.builder().data(productDTO).message("Created/Updated a new Product of id " + product.getPid() + " and name = " + product.getPname()).stats(true).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Delete a product by ID")
    @DeleteMapping("product/delete/{pid}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request payload"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<FooResponse> delete(@PathVariable long pid) {
        ProductDTO productDTO = productService.findById(pid);
        productService.delete(pid);
        FooResponse response = FooResponse.builder().data(productDTO).message("Deleted a Product of id " + pid).stats(true).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Perform an asynchronous task")
    @GetMapping("/async-task")
    public ResponseEntity<FooResponse> performAsyncTask() throws ExecutionException, InterruptedException {
        CompletableFuture<String> msg = productService.AsyncTask();
        System.out.println("This instruction is getting executed and not waiting for the above called function");

        // Now I'm gonna wait until the async function returns the msg to build my response
        String message = msg.get();
        FooResponse response = FooResponse.builder().message(message).stats(true).build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
