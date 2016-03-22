<?php
$con=mysqli_connect("example.com","username","password","my_db");
$sql="INSERT INTO table1 (FirstName, LastName, Age) VALUES ('admin', 'admin','adminstrator')";
if (mysqli_query($con,$sql))
{
   echo "Values have been inserted successfully";
}
?>