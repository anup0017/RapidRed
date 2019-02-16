<?php

$servername = "localhost";
$username = "root";
$password = "";
$dbname = "rapidred";

$conn = mysqli_connect($servername, $username, $password, $dbname);

if(!$conn){
    die("Error Connection");
}
?>