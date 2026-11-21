package cl.duoc.wim100_backend.controller;
 
import cl.duoc.wim100_backend.dto.EstadoRequest;
import cl.duoc.wim100_backend.dto.PostulanteRequest;
import cl.duoc.wim100_backend.model.Postulante;
import cl.duoc.wim100_backend.service.PostulanteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
 
import java.util.List;
 
@RestController
@RequestMapping("/api/postulantes")
public class PostulanteController {
 
    private final PostulanteService service;
 
    public PostulanteController(PostulanteService service) {
        this.service = service;
    }
 
    @GetMapping
    public List<Postulante> listar() {
        return service.listar();
    }
 
    @GetMapping("/{id}")
    public Postulante obtener(@PathVariable Long id) {
        return service.obtener(id);
    }
 
    @PostMapping
    public ResponseEntity<Postulante> crear(@Valid @RequestBody PostulanteRequest req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(req));
    }
 
    @PutMapping("/{id}")
    public Postulante actualizar(@PathVariable Long id, @Valid @RequestBody PostulanteRequest req) {
        return service.actualizar(id, req);
    }
 
    @PatchMapping("/{id}/estado")
    public Postulante cambiarEstado(@PathVariable Long id, @Valid @RequestBody EstadoRequest req) {
        return service.cambiarEstado(id, req.estado());
    }
 
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
