package mobi.foo.training.product.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Table(name = "Product")
@Entity
@Data
public class Product {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long pid;

    @NotNull(message = "Product name is required")
    @Size(min = 1, max = 10, message = "Product name must be between 1 and 10 characters")
    private String pname;


    public Product(long pid, @NotNull(message = "Product name is required") String pname) {
        this.pid = pid;
        this.pname = pname;
    }
}
