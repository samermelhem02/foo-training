package mobi.foo.training.exceptionHandler;


import mobi.foo.training.FooResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class Handler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<FooResponse> handleArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();

        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        List<String> errorMessages = fieldErrors.stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());
        String errorMessage = String.join(", ", errorMessages);

        FooResponse response = FooResponse.builder()
                .message(errorMessage)
                .stats(false)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
