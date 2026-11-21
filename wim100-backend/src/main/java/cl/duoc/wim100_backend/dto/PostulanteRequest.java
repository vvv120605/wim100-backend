package cl.duoc.wim100_backend.dto;

import jakarta.validation.constraints.*;
 
public record PostulanteRequest(
        @NotBlank(message = "El nombre es obligatorio")
        @Size(min = 3, max = 60, message = "El nombre debe tener entre 3 y 60 caracteres")
        String nombre,
 
        @NotBlank(message = "El correo es obligatorio")
        @Email(message = "Formato de correo inválido")
        String correo,
 
        @NotBlank(message = "El teléfono es obligatorio")
        @Size(max = 20)
        String telefono,
 
        @NotBlank(message = "El área es obligatoria")
        String area,
 
        @NotNull(message = "Indica los años de experiencia")
        @Min(value = 0, message = "No puede ser negativo")
        @Max(value = 50, message = "Máximo 50 años")
        Integer aniosExperiencia,
 
        @NotBlank(message = "El resumen es obligatorio")
        @Size(min = 30, max = 500, message = "El resumen debe tener entre 30 y 500 caracteres")
        String resumenExperiencia,
 
        @NotBlank(message = "El enlace al perfil es obligatorio")
        @Pattern(regexp = "^https://.*", message = "Debe comenzar con https://")
        String linkedinUrl,
 
        String cvNombre,
 
        @NotBlank(message = "Falta quién registra la candidatura")
        String registradaPor
) {}
