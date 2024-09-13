package alpha.zapatos.jwt.Config;

import org.springframework.context.annotation.Bean;  // Importa la anotación para definir un bean en el contexto de Spring.
import org.springframework.context.annotation.Configuration;  // Importa la anotación para marcar la clase como una clase de configuración de Spring.
import org.springframework.security.authentication.AuthenticationManager;  // Importa la clase para gestionar la autenticación.
import org.springframework.security.authentication.AuthenticationProvider;  // Importa la interfaz para proporcionar la autenticación.
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;  // Importa la implementación de AuthenticationProvider basada en un DAO.
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;  // Importa la clase para la configuración de autenticación.
import org.springframework.security.core.userdetails.UserDetailsService;  // Importa la interfaz para cargar detalles de usuario.
import org.springframework.security.core.userdetails.UsernameNotFoundException;  // Importa la excepción lanzada cuando un usuario no es encontrado.
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;  // Importa la clase para codificar contraseñas usando BCrypt.
import org.springframework.security.crypto.password.PasswordEncoder;  // Importa la interfaz para codificar contraseñas.

import alpha.zapatos.jwt.User.UserRepository;  // Importa el repositorio de usuarios.
import lombok.RequiredArgsConstructor;  // Importa la anotación para generar un constructor con campos finales.

@Configuration  // Marca la clase como una clase de configuración que define beans de Spring.
@RequiredArgsConstructor  // Genera un constructor que inyecta las dependencias necesarias (en este caso, UserRepository).
public class AplicationConfig {  // (Nota: La clase debería llamarse 'ApplicationConfig' en lugar de 'AplicationConfig' para corregir el error tipográfico).

    private final UserRepository userRepository;  // Repositorio de usuarios inyectado.

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        // Proporciona el AuthenticationManager a partir de la configuración de autenticación.
        return config.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        // Configura el AuthenticationProvider para autenticar usuarios utilizando un DAO.
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());  // Establece el servicio de detalles de usuario.
        authenticationProvider.setPasswordEncoder(passwordEncoder());  // Establece el codificador de contraseñas.
        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Proporciona un codificador de contraseñas usando BCrypt.
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        // Proporciona un UserDetailsService para cargar detalles del usuario desde el repositorio.
        return username -> userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}

