<?php

$conexion = mysqli_connect("localhost", "root", "", "zapateriaen");

if (!$conexion) {
    die("Conexión fallida: " . mysqli_connect_error());
}
  
?>
