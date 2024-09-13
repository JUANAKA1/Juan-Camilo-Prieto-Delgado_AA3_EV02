package alpha.JMzapateria.controller;

import alpha.JMzapateria.model.Usuario;
import alpha.JMzapateria.services.UsuarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // Indica que esta clase es un controlador REST en Spring
@RequestMapping("/usuario") // Define la ruta base para las peticiones que gestionan usuarios
public class UsuarioController {

    @Autowired // Inyección de dependencias para el servicio de usuarios
    private UsuarioServices usuarioServices;
    
    // Método para crear un nuevo usuario (POST)
    @PostMapping("/nuevo")
    public Usuario newUsuario(@RequestBody Usuario newUsuario) {
        // Llama al servicio para guardar el nuevo usuario
        return this.usuarioServices.mewUsuario(newUsuario);
    }
    
    // Método para obtener todos los usuarios (GET)
    @GetMapping("/mostrar")
    public Iterable<Usuario> getAll() {
        // Llama al servicio para obtener la lista de usuarios
        return usuarioServices.getAll();
    }

    // Método para modificar un usuario existente (POST)
    @PostMapping("/modificar")
    public Usuario updateUsuario(@RequestBody Usuario usuario) {
        // Llama al servicio para modificar los datos del usuario
        return this.usuarioServices.modifyUsuario(usuario);
    }

    // Método para eliminar un usuario según su ID (POST)
    @PostMapping(value = "/id")
    public Boolean deleteUsuario(@PathVariable(value = "id") Long id) {
        // Llama al servicio para eliminar el usuario por su ID
        return this.usuarioServices.deleteUsuario(id);
    }
}
