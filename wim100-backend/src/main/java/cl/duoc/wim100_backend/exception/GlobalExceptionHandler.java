package cl.duoc.wim100_backend.exception;
 
import cl.duoc.wim100_backend.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
 
import java.util.HashMap;
import java.util.Map;
 
@RestControllerAdvice
public class GlobalExceptionHandler {
 
    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<ErrorResponse> noEncontrado(RecursoNoEncontradoException e) {
        return respuesta(HttpStatus.NOT_FOUND, e.getMessage(), Map.of());
    }
 
    @ExceptionHandler(CorreoDuplicadoException.class)
    public ResponseEntity<ErrorResponse> duplicado(CorreoDuplicadoException e) {
        return respuesta(HttpStatus.CONFLICT, e.getMessage(), Map.of());
    }
 
    @ExceptionHandler(CredencialesInvalidasException.class)
    public ResponseEntity<ErrorResponse> credenciales(CredencialesInvalidasException e) {
        return respuesta(HttpStatus.UNAUTHORIZED, e.getMessage(), Map.of());
    }
 
    /** Errores de @Valid: devuelve un mensaje por campo. */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> validacion(MethodArgumentNotValidException e) {
        Map<String, String> errores = new HashMap<>();
        e.getBindingResult().getFieldErrors()
                .forEach(f -> errores.put(f.getField(), f.getDefaultMessage()));
        return respuesta(HttpStatus.BAD_REQUEST, "Datos inválidos", errores);
    }
 
    private ResponseEntity<ErrorResponse> respuesta(HttpStatus status, String mensaje, Map<String, String> errores) {
        return ResponseEntity.status(status).body(new ErrorResponse(status.value(), mensaje, errores));
    }
}
