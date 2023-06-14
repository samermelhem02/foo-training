package mobi.foo.training;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class FooResponse {


    private boolean stats;
    private Object data;
    private String message;

    public FooResponse(){}

    public FooResponse(boolean stats, Object data, String message) {
        this.stats = stats;
        this.data = data;
        this.message = message;
    }
}
