<?php

// Verificar si la sesión ya está iniciada
if (session_status() === PHP_SESSION_NONE) {
    session_start(); // Solo iniciar sesión si no está activa
}

include '../php/conexion_be.php';

// Verificar acceso
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

// Manejo de eliminación de productos
if (isset($_GET['borrar'])) {
    $id = intval($_GET['borrar']);
    
    // Obtener el nombre de la imagen antes de eliminar el producto
    $query = "SELECT imagen FROM productos WHERE id = ?";
    $stmt = mysqli_prepare($conexion, $query);
    mysqli_stmt_bind_param($stmt, 'i', $id);
    mysqli_stmt_execute($stmt);
    mysqli_stmt_bind_result($stmt, $imagen);
    mysqli_stmt_fetch($stmt);
    mysqli_stmt_close($stmt);

    // Eliminar producto
    $query = "DELETE FROM productos WHERE id = ?";
    $stmt = mysqli_prepare($conexion, $query);
    mysqli_stmt_bind_param($stmt, 'i', $id);
    if (mysqli_stmt_execute($stmt)) {
        // Eliminar la imagen del servidor
        if (!empty($imagen)) {
            $ruta_imagen = "../images/productos/" . $imagen;
            if (file_exists($ruta_imagen)) {
                unlink($ruta_imagen);
            }
        }
        echo '
            <script>
                alert("Producto eliminado con éxito.");
                window.location = "agregar_productos.php";
            </script>
        ';
    } else {
        echo '
            <script>
                alert("Error al eliminar el producto.");
                window.location = "agregar_productos.php";
            </script>
        ';
    }
    mysqli_stmt_close($stmt);
}

// Manejo de agregar producto
if (isset($_POST['agregar_producto'])) {
    $nombre = mysqli_real_escape_string($conexion, $_POST['nombre']);
    $descripcion = mysqli_real_escape_string($conexion, $_POST['descripcion']);
    $precio = filter_var($_POST['precio'], FILTER_VALIDATE_FLOAT);
    $talla = mysqli_real_escape_string($conexion, $_POST['talla']);
    $color = mysqli_real_escape_string($conexion, $_POST['color']);
    $cantidad = filter_var($_POST['cantidad'], FILTER_VALIDATE_INT);

    if ($precio === false || $cantidad === false) {
        echo '
            <script>
                alert("Precio o cantidad inválidos.");
                window.location = "agregar_productos.php";
            </script>
        ';
        exit();
    }

    // Manejo de imagen
    if (isset($_FILES['imagen']) && $_FILES['imagen']['error'] == UPLOAD_ERR_OK) {
        $directorio_destino = '../assets/images/productos/';
        $archivo_imagen = $_FILES['imagen'];
        $nombre_imagen = basename($archivo_imagen['name']);
        $ruta_imagen = $directorio_destino . $nombre_imagen;
        $tipo_imagen = strtolower(pathinfo($ruta_imagen, PATHINFO_EXTENSION));
        
        // Validar tipo y tamaño de archivo
        $tipos_validos = ['jpg', 'jpeg', 'png', 'gif'];
        if (in_array($tipo_imagen, $tipos_validos) && $archivo_imagen['size'] < 2 * 1024 * 1024) { // Limite de 2 MB
            // Mover archivo a la carpeta destino
            if (move_uploaded_file($archivo_imagen['tmp_name'], $ruta_imagen)) {
                // Insertar producto en la base de datos
                $query = "INSERT INTO productos (nombre, imagen, descripcion, precio, talla, color, cantidad) VALUES (?, ?, ?, ?, ?, ?, ?)";
                $stmt = mysqli_prepare($conexion, $query);
                mysqli_stmt_bind_param($stmt, 'sssdsii', $nombre, $nombre_imagen, $descripcion, $precio, $talla, $color, $cantidad);
                if (mysqli_stmt_execute($stmt)) {
                    echo '
                        <script>
                            alert("Producto agregado con éxito.");
                            window.location = "agregar_productos.php";
                        </script>
                    ';
                } else {
                    echo '
                        <script>
                            alert("Error al agregar el producto.");
                            window.location = "agregar_productos.php";
                        </script>
                    ';
                }
                mysqli_stmt_close($stmt);
            } else {
                echo '
                    <script>
                        alert("Error al cargar la imagen. Inténtalo de nuevo.");
                        window.location = "agregar_productos.php";
                    </script>
                ';
            }
        } else {
            echo '
                <script>
                    alert("Tipo de archivo no permitido o archivo demasiado grande. Solo se permiten imágenes JPG, JPEG, PNG y GIF, y el tamaño máximo es 2 MB.");
                    window.location = "agregar_productos.php";
                </script>
            ';
        }
    } else {
        echo '
            <script>
                alert("Error al cargar la imagen. Por favor, selecciona una imagen válida.");
                window.location = "agregar_productos.php";
            </script>
        ';
    }
}

// Consulta para obtener productos
$query = "SELECT * FROM productos";
$resultado = mysqli_query($conexion, $query);
?>
