package cl.duoc.wim100_backend.dto;
 
import cl.duoc.wim100_backend.model.EstadoPostulante;
import jakarta.validation.constraints.NotNull;
 
public record EstadoRequest(@NotNull(message = "El estado es obligatorio") EstadoPostulante estado) {}
