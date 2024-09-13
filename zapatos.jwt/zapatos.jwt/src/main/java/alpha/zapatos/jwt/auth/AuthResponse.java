package alpha.zapatos.jwt.auth;

import lombok.AllArgsConstructor;  // Genera un constructor con todos los argumentos.
import lombok.Builder;  // Implementa el patrón de diseño Builder.
import lombok.Data;  // Genera automáticamente getters, setters, toString, equals y hashCode.
import lombok.NoArgsConstructor;  // Genera un constructor sin argumentos.


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {

    // Campo que almacena el token JWT generado para la autenticación del usuario.
    String token;
}