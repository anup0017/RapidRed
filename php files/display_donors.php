<?php

require "init.php";

//$phone = $_GET["phone"];

$response = array();
$sql = "select l.name, l.phone, l.email, l.gender, l.blood_group, l.age, l.address from login_details l, donate d where d.phone = l.phone";

$result = mysqli_query($conn,$sql);
if(mysqli_num_rows($result) > 0) {
    //$response['success'] = 1;
    //$details = array();
    while($row = mysqli_fetch_assoc($result)) {
        array_push($response, $row);        
    }
    //$response['details'] = $details;
}

else {
    //$response['success'] = 0;
    $response['message'] = "No data";
}

echo json_encode($response);

mysqli_close($conn);


?>