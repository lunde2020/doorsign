<?php
header("Location: http://lundeallin/pixelserver/pixelserver.php");
 
 // Load header
$i=0;
$c=0;	
foreach (getallheaders() as $name => $value) 
	{
	  	if ($c==2)
			{
				$xbatterymvtemp = $value;
				$xbatterymv = intval($xbatterymvtemp);				
				$xbatterymv = $value;		
			}
			
		 if ($c==3)
			{
				$xmac = $value;
			}
		
		$timedate="{$c} {$name}:{$value}";	
	
		
		$a=$i;
		$i=$a+20;
		$c++;		
	}	

	
$foldername = str_replace(':','',$xmac);						//$foldername ="18fe34d6a261";			// für Testzwecke
$path = "./data/" . $foldername;
$file = $path . "/data.txt";


$string 					= file_get_contents($file);	
$configContentJsonObject 	= json_decode($string);
$modus						= intval($configContentJsonObject->{'mode'});		//choose template
$headerstring 				= "Location: http://lundeallin/pixelserver/modus" .$modus .".php";
//echo $headerstring;
header($headerstring);



if (!file_exists($path)){
	
// Kopiere Max Musterman aus 000000000000
	mkdir($path, 0777, true);	
	copy("./data/000000000000/data.txt", "$path/data.txt");
	copy("./data/000000000000/pinapp.txt", "$path/pinapp.txt");
	
	$string 					= file_get_contents($file);	
	$configContentJsonObject 	= json_decode($string);
		
	$pinlength 					= 4;
	$charSet 					= '123456789'; 
	$pin 						= '';
	for($a = 0; $a < $pinlength; $a++) $pin .= $charSet[rand(0, strlen($charSet))];
		
	$pinsha1					= sha1($pin);
	$filepinapp 				= $path . "/pinapp.txt";	
	$fp 						= fopen($filepinapp, 'w');
	fwrite($fp, $pinsha1); 
	fclose($fp);

	$information				="Türschild wurde erkannt, tragen Sie nun in der App ein Pin:".$pin ."\n MAC-Adresse:" .$xmac;
			
	$configContentJsonObject->{'pin'} 				= $pin;
	$configContentJsonObject->{'information'} 		= $information;
	$newJsonString 									= json_encode($configContentJsonObject);
	file_put_contents($file, $newJsonString);
	$modus 											= 0;			// mode1 wouldnt be that good!
	
}


//Take Voltage for specific Doorsign every time! Voltage diagram only shows up when mode=1!
$voltagedatacsv = $path . "/voltage.csv";	
	
if ($xbatterymv>2000 && $xbatterymv<5000)			// falls von localhost zugegriffen wird -> keine Batterispannung wird eingetragen
	
{
		$line=array (date("r"), $xbatterymv);
		$handle = fopen($voltagedatacsv, "a");		// Then add your line (fputcsv­Docs):
		fputcsv($handle, $line); 					// $line is an array of string values here
		fclose($handle);							//Then close the handle (fclose­Docs):
}

?>