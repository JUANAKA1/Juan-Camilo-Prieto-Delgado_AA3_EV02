<?php
session_start();
include 'php/conexion_be.php';

if (!isset($_SESSION['usuario']) || $_SESSION['tipo_usuario'] != 'admin') {
    echo '
        <script>
            alert("Acceso denegado. Solo administradores pueden acceder a esta página.");
            window.location = "login.php";
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
    <title>Panel de Administración - Zapatería JM</title>
    <link rel="stylesheet" href="assets/css/stylesadmi.css">
</head>
<body class="body-admin">
    <header class="header-admin">
        <h1 class="titulo-admin">Panel de Administración</h1>
        <nav class="nav-admin">
            <ul class="menu-admin">
                <li class="menu-item-admin"><a href="admin_bienvenida.php" class="menu-link-admin">Inicio</a></li>
                <li class="menu-item-admin"><a href="bienvenida.php" class="menu-link-admin">Pagina Zapateria JM</a></li>
                <li class="menu-item-admin"><a href="admin/agregar_productos.php" class="menu-link-admin">Agregar Productos</a></li>
                <li class="menu-item-admin"><a href="admin/gestionar_usuarios.php" class="menu-link-admin">Gestionar Usuarios</a></li>
                <li class="menu-item-admin"><a href="admin/gestionar_pedidos.php" class="menu-link-admin">Gestionar Pedidos</a></li>
                <li class="menu-item-admin"><a href="php/cerrar_sesion.php" class="menu-link-admin">Cerrar sesión</a></li>
            </ul>
        </nav>
    </header>
    <main class="main-admin">
        <h2 class="subtitulo-admin">Bienvenidos a la Zapatería JM</h2>
        <!-- Aquí puedes agregar el contenido específico de la sección -->
    </main>
    <footer class="footer-admin">
        <p>&copy; 2024 Zapatería JM. Todos los derechos reservados.</p>
    </footer>
</body>
</html>
