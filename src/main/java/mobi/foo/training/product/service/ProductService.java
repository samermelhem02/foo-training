package mobi.foo.training.product.service;

import lombok.RequiredArgsConstructor;
import mobi.foo.training.FooResponse;
import mobi.foo.training.product.dto.ProductDTO;
import mobi.foo.training.product.entity.Product;
import mobi.foo.training.product.repository.ProductRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
@Service
@EnableAsync
public class ProductService {
    private final ProductRepository productRepository;


    @Cacheable("products")
    public List<ProductDTO> findAllV1()
    {
        System.out.println("Executing findAllV1() method logic...");
        List<ProductDTO> res  = new ArrayList<>();
        List<Product> Allproducts  = new ArrayList<>();
        Allproducts = productRepository.findAll();
        for(int i = 0; i < Allproducts.size() ; i++)
        {
            res.add(new ProductDTO(Allproducts.get(i).getPid(),Allproducts.get(i).getPname()));
        }
        return res;
    }


    @Cacheable("products")
    public List<ProductDTO> findAllV2()
    {
        System.out.println("Executing findAllV2() method logic...");
        List<ProductDTO> res  = new ArrayList<>();
        List<Product> Allproducts  = new ArrayList<>();
        Allproducts = productRepository.findAll();
        for(int i = 0; i < Allproducts.size() ; i++)
        {
            res.add(new ProductDTO(Allproducts.get(i).getPid(),Allproducts.get(i).getPname()));
        }
        return res;
    }


    public ProductDTO findByIdV1(Long id)
    {
        List<ProductDTO> productList = this.findAllV1();
        for (ProductDTO productDTO : productList) {
            if (productDTO.getPid() == id) {
                System.out.println("Executing findByIdV1");
                return productDTO;
            }
        }
        return null;

    }

    public ProductDTO findByIdV2(Long id)
    {
        List<ProductDTO> productList = this.findAllV2();
        for (ProductDTO productDTO : productList) {
            if (productDTO.getPid() == id) {
                System.out.println("Executing findByIdV2");
                return productDTO;
            }
        }
        return null;

    }
    @CacheEvict(value = "products", allEntries = true)
    public void save(Product product)
    {
        productRepository.save(product);
    }

    @CacheEvict(value = "products", allEntries = true)
    public void delete(Long id)
    {
        productRepository.deleteById(id);
    }


    @Async
    public CompletableFuture<String> AsyncTask() {
        // Time-consuming task here
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        //when the task is completed, we return the result
        return CompletableFuture.completedFuture("Async task is done");

    }


}
