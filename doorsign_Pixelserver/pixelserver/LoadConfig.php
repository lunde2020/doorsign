<?php
	// if (isset($_GET['mac']) && isset($_GET['pin'])) {
		// $mac = $_GET['mac'];
		// $pin = $_GET['pin'];
		
		// echo $mac;
	// }
	// else {
		// echo "ERROR: mac or pin is missing!";
	// }
	
	
	$mac="18:fe:34:d6:1c:7e";

	$foldername = str_replace(':','',$mac);
	$path = "./data/" . $foldername;
	//save data to specific mac adress
	$file = $path . "/data.txt";
	echo $file;
	
	$string = file_get_contents($file);
	
	
	$configContentJsonObject = json_decode($string);

	
	$mac = $configContentJsonObject->{'mac'};
		echo $mac;
		
		$pin = $configContentJsonObject->{'pin'};
	
	// $name =$json_a->{'name'};
	// echo $name;
	
	
	// foreach ($json_a as $person_name => $person_a) {
		// echo $person_a['status'];
	// }
	
	
?>
