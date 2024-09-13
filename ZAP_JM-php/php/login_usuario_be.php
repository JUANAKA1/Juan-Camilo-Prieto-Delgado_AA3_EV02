<?php
session_start();
include 'conexion_be.php';

$correo = $_POST['correo'];
$contrasena = $_POST['contrasena'];
$contrasena = hash('sha512', $contrasena);
$tipo_usuario = $_POST['tipo_usuario']; // Recoger el tipo de usuario

$validar_login = mysqli_query($conexion, "SELECT * FROM usuarios WHERE correo='$correo' AND contrasena='$contrasena' AND tipo_usuario='$tipo_usuario'");

if (mysqli_num_rows($validar_login) > 0) {
    $_SESSION['usuario'] = $correo;
    $_SESSION['tipo_usuario'] = $tipo_usuario;
    if ($tipo_usuario == 'admin') {
        header("location: ../admin_bienvenida.php"); // Redirigir a la página de bienvenida del administrador
    } else {
        header("location: ../bienvenida.php"); // Redirigir a la página de bienvenida del usuario
    }
    exit();
} else {
    echo '
        <script>
            alert("Usuario no existe, por favor verificar los datos introducidos");
            window.location = "../login.php";
        </script>
    ';
    exit();
}

?>
