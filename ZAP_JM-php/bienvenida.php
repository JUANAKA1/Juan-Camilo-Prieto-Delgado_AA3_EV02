<?php
session_start();

if (!isset($_SESSION['usuario'])) {
    echo '
        <script>
            alert("Por favor debes iniciar sesión");
            window.location ="login.php";
        </script>
    ';
    session_destroy();
    die();
}
?>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bienvenido a JM</title>
</head>
<body>
    <h1>Bienvenidos a la Zapatería JM</h1>
    <a href="php/cerrar_sesion.php">Cerrar sesión</a>
</body>
</html>
