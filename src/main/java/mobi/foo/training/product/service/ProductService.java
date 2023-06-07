package mobi.foo.training.product.service;

import lombok.RequiredArgsConstructor;
import mobi.foo.training.product.dto.ProductDTO;
import mobi.foo.training.product.entity.Product;
import mobi.foo.training.product.repository.ProductRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;


    @Cacheable("products")
    public List<ProductDTO> findAll()
    {
        System.out.println("Executing findAll() method logic...");
        List<ProductDTO> res  = new ArrayList<>();
        List<Product> Allproducts  = new ArrayList<>();
        Allproducts = productRepository.findAll();
        for(int i = 0; i < Allproducts.size() ; i++)
        {
            res.add(new ProductDTO(Allproducts.get(i).getPid(),Allproducts.get(i).getPname()));
        }
        return res;
    }


    public ProductDTO findById(Long id)
    {
        ProductDTO res;
        Optional<Product> product = productRepository.findById(id);

        res = new ProductDTO(product.get().getPid(), product.get().getPname());

        return res;

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




}
