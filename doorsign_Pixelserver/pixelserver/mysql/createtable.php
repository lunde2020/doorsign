<?php
$con=mysqli_connect("example.com","username","password","my_db");
$sql="CREATE TABLE table1(Username CHAR(30),Password CHAR(30),Role CHAR(30))";
if (mysqli_query($con,$sql))
{
   echo "Table have been created successfully";
}
?>