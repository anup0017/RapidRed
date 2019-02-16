<?php

require "init.php";

$phone = $_POST["phone"];
$password = $_POST["password"];

$sql = "select * from login_details where phone = '$phone' and password = '$password'";
$result = mysqli_query($conn,$sql);

if(!mysqli_num_rows($result)>0)
{
    //echo "fail";
    $status = "fail";
    $message = "Lol, your code sucks";
    echo json_encode(array("response"=>$status,"message"=>$message));
}
else
{
    //echo "success";
    $row = mysqli_fetch_assoc($result);
    $name = $row['name'];
    $status = "Welcome ";
    echo json_encode(array($status,$name));
}


mysqli_close($conn);


?>