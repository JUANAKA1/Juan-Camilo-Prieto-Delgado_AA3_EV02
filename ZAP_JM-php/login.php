<?php
session_start();
if (isset($_SESSION['usuario'])) {
    header("location:bienvenida.php");
    exit();
}
?>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login y Registro</title>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;300;400;500;700;900&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="assets/css/style.css">
</head>
<body>
    <main>
        <div class="contenedor__todo">
            <div class="caja__trasera">
                <div class="caja__trasera-login">
                    <h3>¿Ya tienes una cuenta?</h3>
                    <p>Inicia sesión para entrar en la página</p>
                    <button id="btn__iniciar-sesion">Iniciar Sesión</button>
                </div>    
                <div class="caja__trasera-register">
                    <h3>¿Aún no tienes una cuenta?</h3>
                    <p>Regístrate para que puedas iniciar sesión</p>
                    <button id="btn__registrarse">Registrarse</button>
                </div>
            </div>
            <div class="contenedor__login-register">
                <form action="php/login_usuario_be.php" method="POST" class="formulario__login">
                    <h2>Iniciar Sesión</h2>
                    <label for="email_login">Correo Electrónico</label>
                    <input type="email" placeholder="Correo Electrónico" id="email_login" name="correo" required>
                    <label for="password_login">Contraseña</label>
                    <input type="password" placeholder="Contraseña" id="password_login" name="contrasena" required>
                    <label for="tipo_usuario">Tipo de Usuario</label>
                    <select id="tipo_usuario" name="tipo_usuario" required>
                        <option value="usuario">Usuario</option>
                        <option value="admin">Administrador</option>
                    </select>
                    <button>Entrar</button>
                </form>
                <form action="php/registro_usuario_be.php" method="POST" class="formulario__register">
                    <h2>Registrarse</h2>
                    <label for="nombre">Nombre Completo</label>
                    <input type="text" placeholder="Nombre Completo" id="nombre" name="nombre_completo" required>
                    <label for="telefono">Teléfono</label>
                    <input type="tel" placeholder="Teléfono" id="telefono" name="telefono" required>
                    <label for="fecha_nacimiento">Fecha de Nacimiento</label>
                    <input type="date" placeholder="Fecha de Nacimiento" id="fecha_nacimiento" name="fecha_nacimiento" required>
                    <label for="tipo_documento">Tipo de Documento</label>
                    <select id="tipo_documento" name="tipo_documento" required>
                        <option value="cc">Cédula de Ciudadanía</option>
                        <option value="ce">Cédula de Extranjería</option>
                        <option value="ti">Tarjeta de Identidad</option>
                        <option value="ps">Pasaporte</option>
                    </select>
                    <label for="numero_documento">Número de Documento</label>
                    <input type="text" placeholder="Número de Documento" id="numero_documento" name="numero_documento" required>
                    <label for="direccion">Dirección</label>
                    <input type="text" placeholder="Dirección" id="direccion" name="direccion" required>
                    <label for="ciudad">Ciudad</label>
                    <input type="text" placeholder="Ciudad" id="ciudad" name="ciudad" required>
                    <label for="codigo_postal">Código Postal</label>
                    <input type="text" placeholder="Código Postal" id="codigo_postal" name="codigo_postal" required>
                    <label for="email_register">Correo Electrónico</label>
                    <input type="email" placeholder="Correo Electrónico" id="email_register" name="correo" required>
                    <label for="usuario">Usuario</label>
                    <input type="text" placeholder="Usuario" id="usuario" name="usuario" required>
                    <label for="password_register">Contraseña</label>
                    <input type="password" placeholder="Contraseña" id="password_register" name="contrasena" required>
                    <button>Registrarse</button>
                </form>
            </div>
        </div>
    </main>
    <script src="assets/js/script.js"></script>
</body>
</html>
