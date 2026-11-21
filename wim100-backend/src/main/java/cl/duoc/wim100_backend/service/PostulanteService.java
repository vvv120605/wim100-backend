package cl.duoc.wim100_backend.service;
 
import cl.duoc.wim100_backend.dto.PostulanteRequest;
import cl.duoc.wim100_backend.exception.CorreoDuplicadoException;
import cl.duoc.wim100_backend.exception.RecursoNoEncontradoException;
import cl.duoc.wim100_backend.model.EstadoPostulante;
import cl.duoc.wim100_backend.model.Postulante;
import cl.duoc.wim100_backend.repository.PostulanteRepository;
import org.springframework.stereotype.Service;
 
import java.util.List;
 
@Service
public class PostulanteService {
 
    private final PostulanteRepository repository;
 
    public PostulanteService(PostulanteRepository repository) {
        this.repository = repository;
    }
 
    public List<Postulante> listar() {
        return repository.findAllByOrderByFechaRegistroDesc();
    }
 
    public Postulante obtener(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No existe el postulante " + id));
    }
 
    public Postulante crear(PostulanteRequest req) {
        // Regla de negocio: no se registra dos veces el mismo correo
        if (repository.existsByCorreoIgnoreCase(req.correo().trim())) {
            throw new CorreoDuplicadoException("Ya existe una candidatura con este correo");
        }
        Postulante p = new Postulante();
        copiarDatos(req, p);
        p.setEstado(EstadoPostulante.POSTULADA);
        p.setFechaRegistro(System.currentTimeMillis());
        p.setRegistradaPor(req.registradaPor().trim());
        return repository.save(p);
    }
 
    public Postulante actualizar(Long id, PostulanteRequest req) {
        Postulante p = obtener(id);
        if (repository.existsByCorreoIgnoreCaseAndIdNot(req.correo().trim(), id)) {
            throw new CorreoDuplicadoException("Ya existe una candidatura con este correo");
        }
        copiarDatos(req, p); // no cambia estado, fecha ni quién la registró
        return repository.save(p);
    }
 
    public Postulante cambiarEstado(Long id, EstadoPostulante estado) {
        Postulante p = obtener(id);
        p.setEstado(estado);
        return repository.save(p);
    }
 
    public void eliminar(Long id) {
        repository.delete(obtener(id)); // lanza 404 si no existe
    }
 
    private void copiarDatos(PostulanteRequest r, Postulante p) {
        p.setNombre(r.nombre().trim().replaceAll("\\s+", " "));
        p.setCorreo(r.correo().trim().toLowerCase());
        p.setTelefono(r.telefono().trim());
        p.setArea(r.area().trim());
        p.setAniosExperiencia(r.aniosExperiencia());
        p.setResumenExperiencia(r.resumenExperiencia().trim());
        p.setLinkedinUrl(r.linkedinUrl().trim());
        p.setCvNombre(r.cvNombre());
    }
}
