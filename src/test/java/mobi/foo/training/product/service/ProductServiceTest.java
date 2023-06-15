package mobi.foo.training.product.service;
import mobi.foo.training.product.dto.ProductDTO;
import mobi.foo.training.product.entity.Product;
import mobi.foo.training.product.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        productService = new ProductService(productRepository);
    }

    @Test
    void findAllV1_ReturnsProductDTOList() {
        // Arrange
        List<Product> productList = new ArrayList<>();
        productList.add(new Product(1L, "Product 1"));
        productList.add(new Product(2L, "Product 2"));
        when(productRepository.findAll()).thenReturn(productList);

        // Act
        List<ProductDTO> result = productService.findAllV1();

        // Assert
        assertEquals(2, result.size());

    }

    @Test
    void findAllV2_ReturnsProductDTOList() {
        // Arrange
        List<Product> productList = new ArrayList<>();
        productList.add(new Product(1L, "Product 1"));
        productList.add(new Product(2L, "Product 2"));
        when(productRepository.findAll()).thenReturn(productList);

        // Act
        List<ProductDTO> result = productService.findAllV2();

        // Assert
        assertEquals(2, result.size());

    }

    @Test
    void findByIdV1_ReturnsProductDTO() {
        // Arrange
        Product p =  new Product(1L, "Product 1");

        when(productRepository.findById(1L)).thenReturn(Optional.of(p));
        // Act
        ProductDTO pdto = new ProductDTO(p.getPid(),p.getPname());

        // Assert
        assertEquals(pdto, productService.findByIdV1(p.getPid()));

    }

    @Test
    void findByIdV2_ReturnsProductDTO() {
        Product p =  new Product(1L, "Product 1");

        when(productRepository.findById(1L)).thenReturn(Optional.of(p));
        // Act
        ProductDTO pdto = new ProductDTO(p.getPid(),p.getPname());

        // Assert
        assertEquals(pdto, productService.findByIdV2(p.getPid()));
    }

    @Test
    void save_CallsProductRepositorySave() {
        // Arrange
        Product product = new Product(1L, "Product 1");

        // Act
        productService.save(product);

        // Assert
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void delete_CallsProductRepositoryDeleteById() {
        // Arrange
        Long productId = 1L;

        // Act
        productService.delete(productId);

        // Assert
        verify(productRepository, times(1)).deleteById(productId);
    }

}
