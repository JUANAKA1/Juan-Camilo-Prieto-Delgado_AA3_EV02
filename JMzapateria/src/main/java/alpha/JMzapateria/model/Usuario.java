package alpha.JMzapateria.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity // Anotación que marca esta clase como una entidad JPA, mapeada a una tabla en la base de datos
@Data   // Anotación de Lombok que genera automáticamente getters, setters, equals, hashCode y toString
public class Usuario {

    @Id // Indica que este campo es la clave primaria de la entidad
    @Column(name = "iduser") // Especifica que este campo está mapeado a la columna "Id usuario" en la base de datos
    private Long iduser; 
    
    @Column(name = "Nombres", nullable = false, length = 20) // Define las propiedades de la columna "Nombres", que es obligatoria y con una longitud máxima de 20 caracteres
    private String nomuser; 

    @Column(name = "Password", nullable = false) // Define que la columna "Password" es obligatoria
    private String password;

    @Column(name = "Email", unique = true, nullable = false) // Define que la columna "Email" es única y obligatoria
    private String email;
    
    @Column(name = "Telefono", nullable = false) // Define que la columna "Telefono" es obligatoria
    private String telefono;
}
