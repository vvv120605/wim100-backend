package cl.duoc.wim100_backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
 
@Entity
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
public class Usuario {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    @Column(nullable = false, unique = true, length = 40)
    private String usuario;
 
    /** Hash BCrypt (60 caracteres), nunca la contraseña en texto plano. */
    @Column(nullable = false, length = 100)
    private String password;
 
    @Column(nullable = false, length = 80)
    private String nombre;
 
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Rol rol;
 
    public Usuario(String usuario, String password, String nombre, Rol rol) {
        this.usuario = usuario;
        this.password = password;
        this.nombre = nombre;
        this.rol = rol;
    }
}
