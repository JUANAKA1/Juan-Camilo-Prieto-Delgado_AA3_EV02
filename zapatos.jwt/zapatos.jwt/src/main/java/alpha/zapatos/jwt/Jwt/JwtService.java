package alpha.zapatos.jwt.Jwt;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;  // Importa los detalles del usuario
import org.springframework.stereotype.Service;  // Marca la clase como un servicio gestionado por Spring

import io.jsonwebtoken.Claims;  // Importa la clase Claims para manejar reclamaciones del JWT
import io.jsonwebtoken.Jwts;  // Importa la clase Jwts para crear y analizar JWTs
import io.jsonwebtoken.SignatureAlgorithm;  // Importa el algoritmo de firma para JWTs
import io.jsonwebtoken.io.Decoders;  // Importa la clase Decoders para decodificar la clave
import io.jsonwebtoken.security.Keys;  // Importa la clase Keys para crear la clave HMAC

@Service  // Marca la clase como un servicio de Spring
public class JwtService {

    private static final String SECRET_KEY = "123";  // Clave secreta para firmar los tokens

    // Genera un token JWT para un usuario
    public String getToken(UserDetails user) {
        return getToken(new HashMap<>(), user);
    }

    // Genera un token JWT con datos adicionales (extraClaims)
    private String getToken(Map<String, Object> extraClaims, UserDetails user) {
        return Jwts.builder()
            .setClaims(extraClaims)  // Establece los reclamos adicionales, si los hay
            .setSubject(user.getUsername())  // Establece el sujeto (nombre de usuario) del token
            .setIssuedAt(new Date(System.currentTimeMillis()))  // Establece la fecha de emisión del token
            .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))  // Establece la fecha de expiración (24 horas)
            .signWith(getKey(), SignatureAlgorithm.HS256)  // Firma el token con la clave secreta y el algoritmo HS256
            .compact();  // Construye el token JWT
    }

    // Obtiene la clave para firmar el token
    private Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);  // Decodifica la clave secreta
        return Keys.hmacShaKeyFor(keyBytes);  // Crea una clave HMAC para la firma
    }

    // Obtiene el nombre de usuario del token
    public String getUsernameFromToken(String token) {
        return getClaims(token, Claims::getSubject);  // Extrae el nombre de usuario del token
    }

    // Verifica si el token es válido para el usuario dado
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);  // Obtiene el nombre de usuario del token
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));  // Verifica si el token es válido y no ha expirado
    }

    // Obtiene todas las reclamaciones del token
    private Claims getAllClaims(String token) {
        return Jwts.parserBuilder()
            .setSigningKey(getKey())  // Establece la clave de firma para analizar el token
            .build()
            .parseClaimsJws(token)  // Analiza el token y obtiene las reclamaciones
            .getBody();  // Obtiene el cuerpo del JWT que contiene las reclamaciones
    }

    // Obtiene datos específicos de las reclamaciones del token
    public <T> T getClaims(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaims(token);  // Obtiene todas las reclamaciones del token
        return claimsResolver.apply(claims);  // Aplica la función para obtener datos específicos
    }

    // Obtiene la fecha de expiración del token
    private Date getExpiration(String token) {
        return getClaims(token, Claims::getExpiration);  // Extrae la fecha de expiración del token
    }

    // Verifica si el token ha expirado
    private Boolean isTokenExpired(String token) {
        return getExpiration(token).before(new Date());  // Compara la fecha de expiración con la fecha actual
    }
}


    