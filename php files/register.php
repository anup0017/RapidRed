<?php

require "init.php";

$name = $_POST["name"];
$phone = $_POST["phone"];
$email = $_POST["email"];
$gender = $_POST["gender"];
$age = $_POST["age"];
$blood_group = $_POST["blood_group"];
$address = $_POST["address"];
$password = $_POST["password"];

$sql = "select * from login_details where phone = '$phone'";
$result = mysqli_query($conn,$sql);

if(mysqli_num_rows($result)>0)
{
    $status = "exist";
}
else
{
    $sql = "insert into login_details values('$name', '$phone', '$email', '$gender', '$age', '$blood_group', '$address', '$password');";
    if(mysqli_query($conn,$sql))
    {
        $status = "ok";
    }
    else
    {
        $status = "error";
    }
}

echo json_encode(array("response"=>$status));


?>