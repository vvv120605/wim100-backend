package cl.duoc.wim100_backend.exception;
 
public class CredencialesInvalidasException extends RuntimeException {
    public CredencialesInvalidasException(String mensaje) { super(mensaje); }
}
