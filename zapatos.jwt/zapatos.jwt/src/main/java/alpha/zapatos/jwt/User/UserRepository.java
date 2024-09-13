package alpha.zapatos.jwt.User;

import java.util.Optional;  // Importa la clase para manejar valores opcionales.
import org.springframework.data.jpa.repository.JpaRepository;  // Importa la interfaz para el repositorio JPA.

public interface UserRepository extends JpaRepository<User, Integer> {
    // Método para encontrar un usuario por su nombre de usuario.
    Optional<User> findByUsername(String username);
}
