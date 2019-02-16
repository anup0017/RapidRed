<?php

require "init.php";

//$phone = $_GET["phone"];

$response = array();
$sql = "select name, phone, email, gender, blood_group, age, address from login_details";

$result = mysqli_query($conn,$sql);
if(mysqli_num_rows($result) > 0) {
    $response['success'] = 1;
    $details = array();
    while($row = mysqli_fetch_assoc($result)) {
        array_push($details, $row);        
    }
    $response['details'] = $details;
}

else {
    $response['success'] = 0;
    $response['message'] = "No data";
}

echo json_encode($response);

mysqli_close($conn);


?>