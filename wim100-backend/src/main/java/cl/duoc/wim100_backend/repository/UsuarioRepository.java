package cl.duoc.wim100_backend.repository;
 
import cl.duoc.wim100_backend.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
 
import java.util.Optional;
 
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
 
    Optional<Usuario> findByUsuario(String usuario);
}
