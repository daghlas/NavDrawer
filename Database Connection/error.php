<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['name']) && isset($_POST['image']) && isset($_POST['description'])) {
    if ($db->dbConnect()) {
        if ($db->submit("errordata", $_POST['name'], $_POST['image'], $_POST['description'])) {
            echo "Uploaded Successfully";
        } else echo "Upload Failed";
    } else echo "Error: Database connection";
} else echo "All fields are required";
?>