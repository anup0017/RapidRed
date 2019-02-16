<?php

require "init.php";

$phone = $_POST["phone"];

$sql = "select * from login_details where phone = '$phone'";
$result = mysqli_query($conn,$sql);

if(mysqli_num_rows($result)>0)
{
    $sql = "insert into donate values('$phone');";
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
    $status = "does not exist";
}

echo json_encode(array("response"=>$status));


?>