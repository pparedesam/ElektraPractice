package elektra.exercise.two.util;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Data
public class ApiResponse<T> {
    private String timestamp;
    private T data;
    private int code;
    private String message;
    private List<String> errors;

    public ApiResponse(HttpStatus status, String message, T data, List<String> errors) {
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        this.data = data;
        this.code = status.value();
        this.message = message;
        this.errors = errors;
    }

    public static <T> ApiResponse<T> success(HttpStatus status, String message, T data) {
        return new ApiResponse<>(status, message, data, List.of());
    }

    public static <T> ApiResponse<T> failure(HttpStatus status, String message, List<String> errors) {
        return new ApiResponse<>(status, message, null, errors);
    }
}
