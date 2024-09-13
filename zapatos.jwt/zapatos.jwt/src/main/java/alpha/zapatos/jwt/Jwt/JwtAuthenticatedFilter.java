package alpha.zapatos.jwt.Jwt;

import java.io.IOException;  // Importa la clase para manejar excepciones de entrada/salida.

import org.springframework.http.HttpHeaders;  // Importa la clase para manejar encabezados HTTP.
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;  // Importa la clase para manejar la autenticación de usuario basada en nombre de usuario y contraseña.
import org.springframework.security.core.context.SecurityContextHolder;  // Importa la clase para manejar el contexto de seguridad.
import org.springframework.security.core.userdetails.UserDetails;  // Importa la interfaz para los detalles de usuario.
import org.springframework.security.core.userdetails.UserDetailsService;  // Importa la interfaz para cargar detalles de usuario.
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;  // Importa la clase para construir detalles de autenticación web.
import org.springframework.stereotype.Component;  // Importa la anotación para definir un componente de Spring.
import org.springframework.util.StringUtils;  // Importa la clase para operaciones de cadenas de texto.
import org.springframework.web.filter.OncePerRequestFilter;  // Importa la clase base para filtros que se ejecutan una vez por solicitud.

import jakarta.servlet.FilterChain;  // Importa la interfaz para la cadena de filtros de servlet.
import jakarta.servlet.ServletException;  // Importa la clase para manejar excepciones de servlet.
import jakarta.servlet.http.HttpServletRequest;  // Importa la clase para manejar solicitudes HTTP.
import jakarta.servlet.http.HttpServletResponse;  // Importa la clase para manejar respuestas HTTP.
import lombok.RequiredArgsConstructor;  // Importa la anotación para generar un constructor con campos finales.

@Component  // Marca la clase como un componente de Spring, para que sea detectada automáticamente durante el escaneo de componentes.
@RequiredArgsConstructor  // Genera un constructor que inyecta las dependencias necesarias (en este caso, JwtService y UserDetailsService).
public class JwtAuthenticatedFilter extends OncePerRequestFilter {
    private final JwtService jwtService;  // Servicio para manejar la lógica de JWT.
    private final UserDetailsService userDetailsService;  // Servicio para cargar detalles del usuario.

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        final String token = getTokenFromRequest(request);  // Obtiene el token JWT del encabezado de la solicitud.
        final String username;
        
        // Si no hay token, continúa con la cadena de filtros sin hacer nada más.
        if (token == null) {
            filterChain.doFilter(request, response);
            return;
        }

        // Obtiene el nombre de usuario del token JWT.
        username = jwtService.getUsernameFromToken(token);

        // Si el nombre de usuario es válido y no hay autenticación en el contexto de seguridad, realiza la autenticación.
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);  // Carga los detalles del usuario desde el servicio de detalles del usuario.

            // Si el token es válido para el usuario, configura el contexto de seguridad.
            if (jwtService.isTokenValid(token, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));  // Establece los detalles de autenticación web.
                SecurityContextHolder.getContext().setAuthentication(authToken);  // Configura el contexto de seguridad con la autenticación.
            }
        }

        // Continúa con la cadena de filtros, procesando la solicitud.
        filterChain.doFilter(request, response);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);  // Obtiene el encabezado de autorización de la solicitud.

        // Si el encabezado de autorización está presente y comienza con "Bearer ", extrae el token JWT.
        if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);  // Elimina "Bearer " del token.
        }

        return null;  // Retorna null si no se encuentra un token válido.
    }
}


