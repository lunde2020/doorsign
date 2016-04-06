<?php
//$city = $_GET['Karlsruhe'];
 
if(isset($city))
{
    $url = "http://api.openweathermap.org/data/2.5/weather?q=Karlsruhe,de&appid=99b12edc4c69580c16e6d7e11646a046";
    $curl = curl_init();
    $headers = array();
    curl_setopt($curl, CURLOPT_HTTPHEADER, $headers);
    curl_setopt($curl, CURLOPT_HEADER, 0);
    curl_setopt($curl, CURLOPT_RETURNTRANSFER, true);
    curl_setopt($curl, CURLOPT_URL, $url);
    curl_setopt($curl, CURLOPT_TIMEOUT, 30);
    $json = curl_exec($curl);
    curl_close($curl);
    
    $data = json_decode($json);
	
echo $data->list[0]->name;
 
    if(!empty($data->list[0]->name)) {
    ?>
    <div>
       Stadt: <strong><?php echo $data->list[0]->name ?></strong><br />
       Aktuell:  <strong><?php echo number_format($data->list[0]->main->temp - 273.15, 1, ',', '') ?> ° C </strong><br />
       Temperatur (heute):<br />
       min. <?php echo number_format($data->list[0]->main->temp_min - 273.15, 1, ',', '') ?> ° C<br />
       max. <?php echo number_format($data->list[0]->main->temp_max - 273.15, 1, ',', '') ?> ° C
    </div>
    <?php
    }
}



// fpassthru(fopen("http://localhost:8080/?http://meuk.spritesserver.nl/eink/", "r"));
// fpassthru(fopen("einkimg.bin", "r"));
// header("Location: http://lundeallin/pixelserver/pixelserver.php");

// Load header
// // $i=0;
// // $c=0;	
// // foreach (getallheaders() as $name => $value) 
	// // {
	  	// // if ($c==2)
			// // {
				// // $xbatterymvtemp = $value;
				// // $xbatterymv = intval($xbatterymvtemp);				
				// // $xbatterymv = $value;		
			// // }
			
		 // // if ($c==3)
			// // {
				// // $xmac = $value;
			// // }
		
		// // $timedate="{$c} {$name}:{$value}";	
	
		
		// // $a=$i;
		// // $i=$a+20;
		// // $c++;		
	// // }	

		
	






    // // $data= file_get_contents("http://api.openweathermap.org/data/2.5/weather?q=Karlsruhe,de&appid=99b12edc4c69580c16e6d7e11646a046");
	
	// // $configContentJsonObject = json_decode($data);

// // $test 				= $configContentJsonObject->{'coord.lon'};		

	
// // echo $test;
	
?>





	
