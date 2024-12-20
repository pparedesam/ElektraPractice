package elektra.exercise.two.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import elektra.exercise.two.util.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({MethodArgumentNotValidException.class, HttpMessageNotReadableException.class})
    public ResponseEntity<ApiResponse> handleValidationExceptions(Exception ex) {
        List<String> errors = new ArrayList<>();

        if (ex instanceof HttpMessageNotReadableException) {
            Throwable cause = (ex).getCause();
            if (cause instanceof InvalidFormatException) {
                InvalidFormatException ife = (InvalidFormatException) cause;
                String fieldName = ife.getPath().get(0).getFieldName();
                String validValues = ife.getTargetType().isEnum()
                        ? String.join(", ", Arrays.stream(ife.getTargetType().getEnumConstants())
                        .map(Object::toString)
                        .toList())
                        : "Valores esperados inválidos";
                errors.add(String.format("El valor '%s' para el campo '%s' no es válido. Valores aceptados: %s",
                        ife.getValue(), fieldName, validValues));
            }
        }

        if (ex instanceof MethodArgumentNotValidException) {
            BindingResult result = ((MethodArgumentNotValidException) ex).getBindingResult();
            errors.addAll(result.getFieldErrors().stream()
                    .map(fieldError -> String.format("El campo '%s': %s", fieldError.getField(), fieldError.getDefaultMessage()))
                    .collect(Collectors.toList()));
        }

        ApiResponse response = ApiResponse.failure(HttpStatus.BAD_REQUEST, "Errores de validación y formato", errors);
        response.setTimestamp(LocalDateTime.now().toString());
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        ApiResponse response = ApiResponse.failure(
                HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                Collections.singletonList("Argumento no válido")
        );
        response.setTimestamp(LocalDateTime.now().toString());
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(feign.FeignException.class)
    public ResponseEntity<ApiResponse> handleFeignException(feign.FeignException ex) {
        HttpStatus status = HttpStatus.resolve(ex.status());
        String message = "Error en la comunicación con el servicio externo";
        List<String> errors = Collections.singletonList(ex.getMessage());

        ApiResponse response = ApiResponse.failure(
                status != null ? status : HttpStatus.INTERNAL_SERVER_ERROR,
                message,
                errors
        );
        response.setTimestamp(LocalDateTime.now().toString());
        return ResponseEntity.status(status != null ? status : HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}

