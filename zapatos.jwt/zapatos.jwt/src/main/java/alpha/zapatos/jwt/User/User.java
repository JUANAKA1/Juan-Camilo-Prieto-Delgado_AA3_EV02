package alpha.zapatos.jwt.User;

import java.util.Collection;  // Importa la interfaz para manejar colecciones de objetos.
import java.util.List;  // Importa la clase para manejar listas.

import org.springframework.security.core.GrantedAuthority;  // Importa la interfaz para las autoridades concedidas.
import org.springframework.security.core.authority.SimpleGrantedAuthority;  // Importa la clase para representar una autoridad concedida.
import org.springframework.security.core.userdetails.UserDetails;  // Importa la interfaz para manejar detalles del usuario en Spring Security.

import jakarta.persistence.Column;  // Importa la anotación para definir columnas en una base de datos.
import jakarta.persistence.Entity;  // Importa la anotación para definir una entidad de JPA.
import jakarta.persistence.GeneratedValue;  // Importa la anotación para definir cómo se generan los valores de una columna.
import jakarta.persistence.Id;  // Importa la anotación para definir la clave primaria de una entidad.
import jakarta.persistence.Table;  // Importa la anotación para definir la tabla de la base de datos.
import jakarta.persistence.UniqueConstraint;  // Importa la anotación para definir restricciones de unicidad en columnas.

import lombok.AllArgsConstructor;  // Importa la anotación para generar un constructor con todos los argumentos.
import lombok.Builder;  // Importa la anotación para implementar el patrón de diseño Builder.
import lombok.Data;  // Importa la anotación para generar automáticamente getters, setters, toString, equals y hashCode.
import lombok.NoArgsConstructor;  // Importa la anotación para generar un constructor sin argumentos.

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity  // Marca la clase como una entidad JPA.
@Table(name = "user", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})  // Define la tabla de la base de datos y establece una restricción de unicidad en la columna "username".
public class User implements UserDetails {

    @Id
    @GeneratedValue  // Genera el valor de la columna automáticamente.
    Integer id;  // Identificador único del usuario.

    @Column(nullable = false)  // Define la columna como no nula.
    String username;  // Nombre de usuario del usuario.

    String lastname;  // Apellido del usuario.
    String firstname;  // Primer nombre del usuario.
    String country;  // País de origen del usuario.
    String password;  // Contraseña del usuario.
    Role role;  // Rol del usuario (definido por la enumeración Role).

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Devuelve las autoridades concedidas al usuario como una lista de SimpleGrantedAuthority.
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        // Indica si la cuenta del usuario no ha expirado. Aquí siempre retorna true.
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // Indica si la cuenta del usuario no está bloqueada. Aquí siempre retorna true.
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // Indica si las credenciales del usuario no han expirado. Aquí siempre retorna true.
        return true;
    }

    @Override
    public boolean isEnabled() {
        // Indica si la cuenta del usuario está habilitada. Aquí siempre retorna true.
        return true;
    }
}
