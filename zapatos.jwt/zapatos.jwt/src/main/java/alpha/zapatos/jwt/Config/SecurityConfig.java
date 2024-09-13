package alpha.zapatos.jwt.Config;

import org.springframework.context.annotation.Bean;  // Importa la anotación para definir un bean en el contexto de Spring.
import org.springframework.context.annotation.Configuration;  // Importa la anotación para marcar la clase como una clase de configuración de Spring.
import org.springframework.security.authentication.AuthenticationProvider;  // Importa la interfaz para proporcionar la autenticación.
import org.springframework.security.config.annotation.web.builders.HttpSecurity;  // Importa la clase para configurar la seguridad HTTP.
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;  // Importa la anotación para habilitar la configuración de seguridad web.
import org.springframework.security.config.http.SessionCreationPolicy;  // Importa la clase para definir la política de creación de sesiones.
import org.springframework.security.web.SecurityFilterChain;  // Importa la clase para definir la cadena de filtros de seguridad.
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;  // Importa el filtro para la autenticación basada en nombre de usuario y contraseña.

import alpha.zapatos.jwt.Jwt.JwtAuthenticatedFilter;  // Importa el filtro personalizado para la autenticación JWT.

import lombok.RequiredArgsConstructor;  // Importa la anotación para generar un constructor con campos finales.

@Configuration  // Marca la clase como una clase de configuración que define beans para el contexto de Spring.
@EnableWebSecurity  // Habilita la configuración de seguridad web en la aplicación.
@RequiredArgsConstructor  // Genera un constructor que inyecta las dependencias necesarias (en este caso, JwtAuthenticatedFilter y AuthenticationProvider).
public class SecurityConfig {

    private final JwtAuthenticatedFilter jwtAuthenticatedFilter;  // Filtro personalizado para manejar la autenticación JWT.
    private final AuthenticationProvider authProvider;  // Proveedor de autenticación configurado para la aplicación.

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Configura la cadena de filtros de seguridad para la aplicación.
        return http
            .csrf(csrf -> csrf.disable())  // Desactiva la protección CSRF (Cross-Site Request Forgery). Esto es común en aplicaciones que usan JWT.
            .authorizeHttpRequests(authRequests -> 
                authRequests
                    .requestMatchers("/auth/**").permitAll()  // Permite el acceso sin autenticación a las rutas que comienzan con /auth.
                    .anyRequest().authenticated()  // Requiere autenticación para cualquier otra solicitud.
            )
            .sessionManagement(sessionManager -> 
                sessionManager
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))  // Configura la política de sesiones como sin estado (stateless), adecuado para aplicaciones basadas en JWT.
            .authenticationProvider(authProvider)  // Establece el proveedor de autenticación.
            .addFilterBefore(jwtAuthenticatedFilter, UsernamePasswordAuthenticationFilter.class)  // Añade el filtro JWT antes del filtro de autenticación por nombre de usuario y contraseña.
            .build();  // Construye y devuelve la cadena de filtros de seguridad configurada.
    }
}
