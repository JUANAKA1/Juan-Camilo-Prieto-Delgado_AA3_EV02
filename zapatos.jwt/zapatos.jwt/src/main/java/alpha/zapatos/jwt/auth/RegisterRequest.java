package alpha.zapatos.jwt.auth;

// Lombok se utiliza para reducir la cantidad de código repetitivo como constructores, getters, setters, etc.
import lombok.AllArgsConstructor;  // Genera un constructor con todos los argumentos
import lombok.Builder;  // Implementa el patrón de diseño Builder
import lombok.Data;  // Genera automáticamente getters, setters, toString, equals, y hashCode
import lombok.NoArgsConstructor;  // Genera un constructor sin argumentos

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    String username;
    String password;
    String firstname;
    String lastname;
    String country;

}
