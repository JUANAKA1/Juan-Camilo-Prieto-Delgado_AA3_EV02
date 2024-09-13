package alpha.zapatos.jwt.auth;

import org.springframework.security.authentication.AuthenticationManager;  // Importa el manager de autenticación de Spring Security
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;  // Importa el token de autenticación para nombre de usuario y contraseña
import org.springframework.security.core.userdetails.UserDetails;  // Importa los detalles del usuario
import org.springframework.security.crypto.password.PasswordEncoder;  // Importa el codificador de contraseñas
import org.springframework.stereotype.Service;  // Marca la clase como un servicio gestionado por Spring

import alpha.zapatos.jwt.Jwt.JwtService;  // Importa el servicio para manejar JWT
import alpha.zapatos.jwt.User.Role;  // Importa el enum de roles
import alpha.zapatos.jwt.User.User;  // Importa la entidad User
import alpha.zapatos.jwt.User.UserRepository;  // Importa el repositorio de usuarios
import lombok.RequiredArgsConstructor;  // Importa la anotación para generar un constructor con los campos finales

@Service  // Marca la clase como un servicio de Spring
@RequiredArgsConstructor  // Genera un constructor con todos los campos finales necesarios para la inyección de dependencias
public class AuthService {

    private final UserRepository userRepository;  // Repositorio para manejar usuarios
    private final JwtService jwtService;  // Servicio para manejar JWT
    private final PasswordEncoder passwordEncoder;  // Codificador de contraseñas
    private final AuthenticationManager authenticationManager;  // Gestor de autenticación

    // Método para autenticar a un usuario y devolver un token JWT
    public AuthResponse login(LoginRequest request) {
        // Autentica las credenciales usando el AuthenticationManager
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        // Recupera el usuario a partir del nombre de usuario
        UserDetails user = userRepository.findByUsername(request.getUsername())
            .orElseThrow(() -> new RuntimeException("User not found"));

        // Genera un token JWT para el usuario autenticado
        String token = jwtService.getToken(user);

        // Devuelve la respuesta con el token generado
        return AuthResponse.builder()
            .token(token)
            .build();
    }

    // Método para registrar un nuevo usuario y devolver un token JWT
    public AuthResponse register(RegisterRequest request) {
        // Verifica si el usuario ya existe
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("User already exists");
        }

        // Crea un nuevo objeto User a partir de la solicitud de registro
        User user = User.builder()
            .username(request.getUsername())
            .password(passwordEncoder.encode(request.getPassword()))  // Codifica la contraseña antes de guardarla
            .firstname(request.getFirstname())
            .lastname(request.getLastname())
            .country(request.getCountry())
            .role(Role.USER)  // Asigna el rol de usuario por defecto
            .build();

        // Guarda el nuevo usuario en el repositorio
        userRepository.save(user);

        // Genera un token JWT para el usuario registrado
        String token = jwtService.getToken(user);

        // Devuelve la respuesta con el token generado
        return AuthResponse.builder()
            .token(token)
            .build();
    }
}
