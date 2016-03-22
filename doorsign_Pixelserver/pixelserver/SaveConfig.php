<?php

$configContentJsonString = $_POST['json'];
$configContentJsonObject = json_decode($configContentJsonString);


//search for folder with specific mac-adress choosen in app
$mac = $configContentJsonObject->{'mac'};
$foldername = str_replace(':','',$mac);
$path = "./data/" . $foldername;
//save data to specific mac adress
$file = $path . "/data.txt";



//check if pin is correct
$pin = $configContentJsonObject->{'pin'};
$pinsha1= sha1($pin);

// $filepinok = $path . "/pinapp_start.txt";	
		// $fp = fopen($filepinok, 'w');
		// fwrite($fp, $pinsha1); 
		// fclose($fp);


$filepinapp = $path . "/pinapp.txt";
$handle = fopen($filepinapp, "r");
$pinapp = fread($handle, filesize($filepinapp));
fclose($handle);


//if (!file_exists($path)){
//	mkdir($path, 0777, true);
//}



if ($pinapp == $pinsha1)
{
		$filepinok = $path . "/pinok.txt";	
		$fp = fopen($filepinok, 'w');
		fwrite($fp, "PinOk"); 
		fclose($fp);

		$fp = fopen($file, 'w');
		fwrite($fp, $_POST['json']); 
		fclose($fp);
		
		$delete 	= $configContentJsonObject->{'delete'};

		if ($delete){
			
		$filedelete = $path . "/delete.txt";	
		$fp = fopen($filedelete, 'w');
		fwrite($fp, "delete"); 
		fclose($fp);
		
		$path = escapeshellarg($path);
		exec("rmdir /s /q $path");
		
		
		

		if(!rmdir($path))
				{
					$filedeletefb = $path . "/deletefb.txt";	
					$fp = fopen($filedeletefb, 'w');
					fwrite($fp, "hat nicht geklappt"); 
					fclose($fp);
				}

}
		
		
}

else 
{	
		$filepinok = $path . "/pinok.txt";	
		$fp = fopen($filepinok, 'w');
		fwrite($fp, "PinNOTok"); 
		fclose($fp);
		
	}
?>