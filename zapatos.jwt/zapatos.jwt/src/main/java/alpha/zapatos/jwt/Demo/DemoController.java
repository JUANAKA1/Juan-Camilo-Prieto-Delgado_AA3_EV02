package alpha.zapatos.jwt.Demo;

import org.springframework.web.bind.annotation.PostMapping;  // Importa la anotación para manejar solicitudes POST.
import org.springframework.web.bind.annotation.RequestMapping;  // Importa la anotación para especificar la URL base del controlador.
import org.springframework.web.bind.annotation.RestController;  // Importa la anotación para definir un controlador REST.
import lombok.RequiredArgsConstructor;  // Importa la anotación para generar un constructor con campos finales.

@RestController  // Marca la clase como un controlador REST, lo que significa que los métodos manejarán solicitudes HTTP y devolverán respuestas HTTP.
@RequestMapping("/api/v1")  // Especifica la URL base para todas las solicitudes manejadas por este controlador.
@RequiredArgsConstructor  // Genera un constructor que inyecta las dependencias necesarias (en este caso, ninguna en particular aquí).
public class DemoController {

    @PostMapping(value = "demo")  // Maneja las solicitudes POST en la ruta /api/v1/demo.
    public String welcome() {
        // Devuelve una respuesta simple de texto para la solicitud POST.
        return "welcome from public endpoint";
    }
}

