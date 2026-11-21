package cl.duoc.wim100_backend.model;
 
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
 
@Entity
@Table(name = "postulantes")
@Getter
@Setter
@NoArgsConstructor
public class Postulante {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    @Column(nullable = false, length = 60)
    private String nombre;
 
    @Column(nullable = false, unique = true, length = 120)
    private String correo;
 
    @Column(nullable = false, length = 20)
    private String telefono;
 
    @Column(nullable = false, length = 60)
    private String area;
 
    @Column(nullable = false)
    private Integer aniosExperiencia;
 
    @Column(nullable = false, length = 500)
    private String resumenExperiencia;
 
    @Column(nullable = false, length = 200)
    private String linkedinUrl;
 
    private String cvNombre;
 
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EstadoPostulante estado = EstadoPostulante.POSTULADA;
 
    /** Milisegundos desde 1970, igual que System.currentTimeMillis() en la app. */
    @Column(nullable = false)
    private Long fechaRegistro;
 
    @Column(nullable = false, length = 80)
    private String registradaPor;
}
