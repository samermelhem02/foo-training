package mobi.foo.training.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class ProductDTO implements Serializable {
    private long pid;

    private String pname;






}
