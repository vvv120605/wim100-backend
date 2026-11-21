package cl.duoc.wim100_backend.dto;
 
import cl.duoc.wim100_backend.model.Rol;
 
/** Lo que la app recibe al iniciar sesión. Nunca incluye la contraseña. */
public record LoginResponse(Long id, String usuario, String nombre, Rol rol) {}
