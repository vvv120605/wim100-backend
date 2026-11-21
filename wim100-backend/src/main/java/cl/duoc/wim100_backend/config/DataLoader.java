package cl.duoc.wim100_backend.config;
 
import cl.duoc.wim100_backend.model.EstadoPostulante;
import cl.duoc.wim100_backend.model.Postulante;
import cl.duoc.wim100_backend.model.Rol;
import cl.duoc.wim100_backend.model.Usuario;
import cl.duoc.wim100_backend.repository.PostulanteRepository;
import cl.duoc.wim100_backend.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
 
import java.util.List;
 
@Component
public class DataLoader implements CommandLineRunner {
 
    private final UsuarioRepository usuarios;
    private final PostulanteRepository postulantes;
    private final PasswordEncoder encoder;
 
    public DataLoader(UsuarioRepository usuarios, PostulanteRepository postulantes, PasswordEncoder encoder) {
        this.usuarios = usuarios;
        this.postulantes = postulantes;
        this.encoder = encoder;
    }
 
    @Override
    public void run(String... args) {
        if (usuarios.count() == 0) {
            usuarios.saveAll(List.of(
                    new Usuario("evaluadora1", encoder.encode("wim2026"), "Camila Rojas Demo", Rol.EVALUADORA),
                    new Usuario("evaluadora2", encoder.encode("wim2026"), "Valentina Muñoz Demo", Rol.EVALUADORA),
                    new Usuario("direccion", encoder.encode("wim2026"), "Dirección WIM Demo", Rol.DIRECCION)
            ));
        }
        if (postulantes.count() == 0) {
            postulantes.saveAll(List.of(
                    nueva("Ignacia Valdés Soto", "ignacia.valdes@ejemplo.cl", "+56 9 1111 2201",
                            "Seguridad ofensiva (Red Team)", 9,
                            "Pentester líder en una consultora ficticia. Coordina ejercicios de Red Team.",
                            "https://www.linkedin.com/in/ignacia-valdes-demo"),
                    nueva("Martina Herrera Lagos", "martina.herrera@ejemplo.cl", "+56 9 1111 2202",
                            "Seguridad defensiva (Blue Team)", 7,
                            "Jefa de un SOC ficticio. Diseñó playbooks de detección de incidentes.",
                            "https://www.linkedin.com/in/martina-herrera-demo"),
                    nueva("Fernanda Castillo Rivas", "fernanda.castillo@ejemplo.cl", "+56 9 1111 2203",
                            "Gobierno, riesgo y cumplimiento", 12,
                            "Especialista en ISO 27001 en una empresa ficticia del sector financiero.",
                            "https://www.linkedin.com/in/fernanda-castillo-demo")
            ));
        }
    }
 
    private Postulante nueva(String nombre, String correo, String telefono, String area,
                             int anios, String resumen, String linkedin) {
        Postulante p = new Postulante();
        p.setNombre(nombre);
        p.setCorreo(correo);
        p.setTelefono(telefono);
        p.setArea(area);
        p.setAniosExperiencia(anios);
        p.setResumenExperiencia(resumen);
        p.setLinkedinUrl(linkedin);
        p.setCvNombre("CV_" + nombre.split(" ")[0] + ".pdf");
        p.setEstado(EstadoPostulante.POSTULADA);
        p.setFechaRegistro(System.currentTimeMillis());
        p.setRegistradaPor("Carga inicial");
        return p;
    }
}
