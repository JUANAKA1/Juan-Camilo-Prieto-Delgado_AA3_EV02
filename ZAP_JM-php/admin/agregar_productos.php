<?php
session_start();
include '../php/conexion_be.php';
include 'logica_productos.php';

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
?>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Agregar Productos - Zapatería JM</title>
    <link rel="stylesheet" href="../assets/css/stylesadmi.css">
</head>
<body class="body-admin">
    <header class="header-admin">
        <h1 class="titulo-admin">Panel de Administración</h1>
        <nav class="nav-admin">
            <ul class="menu-admin">
                <li class="menu-item-admin"><a href="../admin_bienvenida.php" class="menu-link-admin">Inicio</a></li>
                <li class="menu-item-admin"><a href="agregar_productos.php" class="menu-link-admin">Agregar Productos</a></li>
                <li class="menu-item-admin"><a href="gestionar_usuarios.php" class="menu-link-admin">Gestionar Usuarios</a></li>
                <li class="menu-item-admin"><a href="gestionar_pedidos.php" class="menu-link-admin">Gestionar Pedidos</a></li>
                <li class="menu-item-admin"><a href="../php/cerrar_sesion.php" class="menu-link-admin">Cerrar sesión</a></li>
            </ul>
        </nav>
    </header>

    <main class="main-admin">
        <section class="formulario-admin">
            <h2 class="subtitulo-admin">Agregar Nuevo Producto</h2>
            <form method="POST" action="agregar_productos.php" enctype="multipart/form-data" class="form-admin">
                <div class="form-group">
                    <label for="nombre" class="label-admin">Nombre</label>
                    <input type="text" id="nombre" name="nombre" class="input-admin" required>
                </div>

                <div class="form-group">
                    <label for="imagen" class="label-admin">Imagen</label>
                    <input type="file" id="imagen" name="imagen" class="input-admin" accept="image/*" required>
                </div>
                
                <div class="form-group">
                    <label for="descripcion" class="label-admin">Descripción</label>
                    <input type="text" id="descripcion" name="descripcion" class="input-admin" required>
                </div>
                
                <div class="form-group">
                    <label for="precio" class="label-admin">Precio</label>
                    <input type="number" id="precio" name="precio" class="input-admin" step="0.01" required>
                </div>
                
                <div class="form-group">
                    <label for="talla" class="label-admin">Talla</label>
                    <input type="text" id="talla" name="talla" class="input-admin" required>
                </div>
                
                <div class="form-group">
                    <label for="color" class="label-admin">Color</label>
                    <input type="text" id="color" name="color" class="input-admin" required>
                </div>
                
                <div class="form-group">
                    <label for="cantidad" class="label-admin">Cantidad</label>
                    <input type="number" id="cantidad" name="cantidad" class="input-admin" required>
                </div>
                
                <button type="submit" name="agregar_producto" class="button-admin">Agregar Producto</button>
            </form>
        </section>

        <section class="productos-admin">
            <h2 class="subtitulo-admin">Productos Actuales</h2>
            <table class="tabla-productos">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nombre</th>
                        <th>Imagen</th>
                        <th>Descripción</th>
                        <th>Precio</th>
                        <th>Talla</th>
                        <th>Color</th>
                        <th>Cantidad</th>
                        <th>Acción</th>
                    </tr>
                </thead>
                <tbody>
                    <?php while ($producto = mysqli_fetch_assoc($resultado)): ?>
                    <tr>
                        <td><?php echo htmlspecialchars($producto['id']); ?></td>
                        <td><?php echo htmlspecialchars($producto['nombre']); ?></td>
                        <td>
                            <img src="../images/productos/<?php echo htmlspecialchars($producto['imagen']); ?>" alt="<?php echo htmlspecialchars($producto['nombre']); ?>" style="width: 100px;">
                        </td>
                        <td><?php echo htmlspecialchars($producto['descripcion']); ?></td>
                        <td><?php echo htmlspecialchars($producto['precio']); ?></td>
                        <td><?php echo htmlspecialchars($producto['talla']); ?></td>
                        <td><?php echo htmlspecialchars($producto['color']); ?></td>
                        <td><?php echo htmlspecialchars($producto['cantidad']); ?></td>
                        <td>
                            <a href="agregar_productos.php?borrar=<?php echo urlencode($producto['id']); ?>" class="button-admin" onclick="return confirm('¿Estás seguro de que quieres eliminar este producto?')">Eliminar</a>
                        </td>
                    </tr>
                    <?php endwhile; ?>
                </tbody>
            </table>
        </section>
    </main>

    <footer class="footer-admin">
        <p>&copy; 2024 Zapatería JM. Todos los derechos reservados.</p>
    </footer>
</body>
</html>
