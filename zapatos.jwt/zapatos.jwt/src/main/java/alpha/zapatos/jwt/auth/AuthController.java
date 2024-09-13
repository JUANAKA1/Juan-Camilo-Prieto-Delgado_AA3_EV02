package alpha.zapatos.jwt.auth;

import org.springframework.http.ResponseEntity;  // Importa ResponseEntity para construir respuestas HTTP.
import org.springframework.web.bind.annotation.PostMapping;  // Importa @PostMapping para manejar solicitudes POST.
import org.springframework.web.bind.annotation.RequestBody;  // Importa @RequestBody para vincular el cuerpo de la solicitud a un objeto.
import org.springframework.web.bind.annotation.RequestMapping;  // Importa @RequestMapping para especificar la URL base del controlador.
import org.springframework.web.bind.annotation.RestController;  // Importa @RestController para definir un controlador REST.

import lombok.RequiredArgsConstructor;  // Importa @RequiredArgsConstructor para generar un constructor con los campos finales.

@RestController  // Marca la clase como un controlador REST, lo que significa que los métodos manejarán solicitudes HTTP y devolverán respuestas HTTP.
@RequestMapping("/auth")  // Especifica la URL base para todas las solicitudes manejadas por este controlador.
@RequiredArgsConstructor  // Genera un constructor que inyecta las dependencias necesarias (en este caso, AuthService).
public class AuthController {

    private final AuthService authService;  // Inyección de AuthService para manejar la lógica de autenticación.

    @PostMapping(value = "login")  // Maneja las solicitudes POST en la ruta /auth/login.
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        // Llama al método de login en AuthService y devuelve una respuesta HTTP con el token JWT.
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping(value = "register")  // Maneja las solicitudes POST en la ruta /auth/register.
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        // Llama al método de registro en AuthService y devuelve una respuesta HTTP con el token JWT.
        return ResponseEntity.ok(authService.register(request));
    }
   
}