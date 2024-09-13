package alpha.JMzapateria.repository;

import alpha.JMzapateria.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

// Interfaz que extiende JpaRepository para proporcionar operaciones CRUD para la entidad Usuario
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
