package cl.duoc.wim100_backend.repository;
 
import cl.duoc.wim100_backend.model.Postulante;
import org.springframework.data.jpa.repository.JpaRepository;
 
import java.util.List;
 
public interface PostulanteRepository extends JpaRepository<Postulante, Long> {
 
    List<Postulante> findAllByOrderByFechaRegistroDesc();
 
    boolean existsByCorreoIgnoreCase(String correo);
 
    boolean existsByCorreoIgnoreCaseAndIdNot(String correo, Long id);
}
