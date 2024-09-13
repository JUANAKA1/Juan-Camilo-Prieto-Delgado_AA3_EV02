package alpha.JMzapateria.services;

import alpha.JMzapateria.model.Usuario;
import alpha.JMzapateria.repository.UsuarioRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service // Anotación que indica que esta clase es un servicio de Spring
public class UsuarioServiceImpl implements UsuarioServices {

    @Autowired // Inyección de dependencias para utilizar el repositorio de usuarios
    private UsuarioRepository usuarioRepository;

    // Método para crear un nuevo usuario
    @Override
    public Usuario mewUsuario(Usuario newUsuario) {
        // Guarda el nuevo usuario en la base de datos
        return usuarioRepository.save(newUsuario);
    }

    // Método para obtener todos los usuarios de la base de datos
    @Override
    public Iterable<Usuario> getAll() {
        // Retorna una lista de todos los usuarios almacenados
        return this.usuarioRepository.findAll();  
    }

    // Método para modificar los datos de un usuario existente
    @Override
    public Usuario modifyUsuario(Usuario usuario) {
        // Busca el usuario por su ID
        Optional<Usuario> usuarioEncontrado = this.usuarioRepository.findById(usuario.getIduser());
        
        // Verifica si el usuario fue encontrado
        if (usuarioEncontrado.isPresent()) {
            // Actualiza los campos del usuario con los nuevos datos
            usuarioEncontrado.get().setEmail(usuario.getEmail());
            usuarioEncontrado.get().setNomuser(usuario.getNomuser());
            usuarioEncontrado.get().setPassword(usuario.getPassword());
            usuarioEncontrado.get().setTelefono(usuario.getTelefono());
            
            // Guarda los cambios del usuario actualizado en la base de datos
            return this.mewUsuario(usuarioEncontrado.get());
        }
        
        // Retorna null si el usuario no fue encontrado
        return null; 
    }

    // Método para eliminar un usuario por su ID
    @Override
    public Boolean deleteUsuario(Long iduser) {
        // Elimina el usuario de la base de datos según su ID
        this.usuarioRepository.deleteById(iduser);
        return true; // Retorna true para indicar que la operación fue exitosa
    }
}
