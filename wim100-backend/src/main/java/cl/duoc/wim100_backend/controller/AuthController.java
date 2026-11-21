package cl.duoc.wim100_backend.controller;
 
import cl.duoc.wim100_backend.dto.LoginRequest;
import cl.duoc.wim100_backend.dto.LoginResponse;
import cl.duoc.wim100_backend.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
 
@RestController
@RequestMapping("/api/auth")
public class AuthController {
 
    private final AuthService service;
 
    public AuthController(AuthService service) {
        this.service = service;
    }
 
    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest req) {
        return service.login(req);
    }
}
