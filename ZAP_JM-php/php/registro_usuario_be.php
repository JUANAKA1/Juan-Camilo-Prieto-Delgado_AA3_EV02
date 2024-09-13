<?php

    include 'conexion_be.php';

    $nombre_completo = $_POST['nombre_completo'];
    $contrasena = $_POST['contrasena'];
    $correo = $_POST['correo'];
    $telefono = $_POST['telefono'];
    $fecha_nacimiento = $_POST['fecha_nacimiento'];
    $tipo_documento = $_POST['tipo_documento'];
    $numero_documento = $_POST['numero_documento'];
    $direccion = $_POST['direccion'];
    $ciudad = $_POST['ciudad'];
    $codigo_postal = $_POST['codigo_postal'];
    $usuario = $_POST['usuario'];
    //encriptar contraseña
    $contrasena =hash('sha512',$contrasena);

    $query = "INSERT INTO usuarios (nombre_completo, telefono, fecha_nacimiento, tipo_documento, numero_documento, direccion, ciudad, codigo_postal, correo, usuario, contrasena) VALUES ('$nombre_completo', '$telefono', '$fecha_nacimiento', '$tipo_documento', '$numero_documento', '$direccion', '$ciudad', '$codigo_postal', '$correo', '$usuario', '$contrasena')";

    /*verificar que el correo no se repita en la base de datos */
    $verificar_correo= mysqli_query($conexion, "SELECt * FROM usuarios WHERE correo = '$correo' ");

    if (mysqli_num_rows($verificar_correo) > 0) {
        echo '
            <script>
                alert("Este correo ya esta registrado, intenta con otro diferente");
                window.location= "../login.php";
            </script>
        ';
       exit();
    }

        /*verificar que el usuario no se repita en la base de datos */
        $verificar_usuario= mysqli_query($conexion, "SELECt * FROM usuarios WHERE usuario= '$usuario' ");

        if (mysqli_num_rows($verificar_usuario) > 0) {
            echo '
                <script>
                    alert("Este usuario ya esta registrado, intenta con otro diferente");
                    window.location= "../login.php";
                </script>
            ';
           exit();
        }

    $ejecutar = mysqli_query($conexion, $query);

    if ($ejecutar) {
        echo '
            <script>
                alert("Usuario registrado exitosamente");
                window.location= "../login.php";
            </script>
        ';
    }else {
        echo '
            <script>
               alert("Error al registrar el usuario, intenta nuevamente");                window.location= "../login.php";
            </script>
        ';
    }
    mysqli_close($conexion);
?>