package mobi.foo.training.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.io.Serializable;

@Data
@AllArgsConstructor
@Builder
public class ProductDTO implements Serializable {
    private long pid;

    private String pname;




}
