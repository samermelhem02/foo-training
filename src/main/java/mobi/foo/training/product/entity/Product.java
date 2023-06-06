package mobi.foo.training.product.entity;


import jakarta.persistence.*;
import lombok.Data;

@Table(name = "Product")
@Entity
@Data
public class Product {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long pid;

    private String pname;


}
