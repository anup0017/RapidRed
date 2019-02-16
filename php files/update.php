<?php

require "init.php";

$name = $_POST["name"];
$phone = $_POST["phone"];
$email = $_POST["email"];
$password = $_POST["password"];

$sql = "select * from login_details where phone = '$phone'";
$result = mysqli_query($conn,$sql);

if(mysqli_num_rows($result)>0)
{
    if($name != null)
    {
        $sql = "update login_details set name = '$name' where phone  = '$phone'";
    }
    else
    {
        $sql = "update login_details set password = '$password' where phone  = '$phone'";
    }
    if(mysqli_query($conn,$sql))
    {
        $status = "ok";
    }
    else
    {
        $status = "error";
    }
}
else
{
    $status = "doesn't exist";
}

echo json_encode(array("response"=>$status));


?>