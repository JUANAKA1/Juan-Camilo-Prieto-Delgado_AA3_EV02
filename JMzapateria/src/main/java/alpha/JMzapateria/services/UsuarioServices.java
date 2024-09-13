package alpha.JMzapateria.services;

import alpha.JMzapateria.model.Usuario;

// Interfaz que define los métodos del servicio para manejar operaciones relacionadas con la entidad Usuario
public interface UsuarioServices {
    
    // Método para crear un nuevo usuario
    Usuario mewUsuario(Usuario newUsuario);
    
    // Método para obtener todos los usuarios
    Iterable<Usuario> getAll();
    
    // Método para modificar un usuario existente
    Usuario modifyUsuario(Usuario usuario);
    
    // Método para eliminar un usuario por su ID
    Boolean deleteUsuario(Long iduser);
}
