<?php

/*
##########################################################
##########################################################

Hints:
======
		- don't rename folders! Keep this construction, 
		otherwise the code will failure!
		- battery voltage function is a suggestion, cant be right...
		normally batterys arent linears
		
*/

// Easter Egg Random
/*srand(time());
$i=rand()%2;=1
if ($i==0) $fn="apconnect.png";
if ($i=) $fn="batempty.png";
*/

$debug		=0;		// DEBUG option			
						// 0->"DisplayMode"		==>	choose this for operation mode!			
						// 1->"Picture-Debug" 	==>	show picture in Browser 						
						// 2->"PHP-Debug"		==>	show debug comments -> empty page no errors	
$refresh 	=60;		// Refresh time in sec
						// 0->"NoWakeUp"		==> choose this for only wake up by reset
						// 3600->"1Hour"		==> wakeup each hour			
$php_header	=0;		// show PHP-Header -> in eigenen modus machen (zeigt Mac-Adresse von Türschild an)			
						// 0->"don't show"		
						// 1->"show" 								
$width 		= 800;	//define display size
$height 	= 600;	//define display size

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
		
		// if ($php_header==1)
			// {
				// imagettftext($img,
					// 20,			
					// 0,					
					// 20, 				
					// 50+$i,					
					// $black,				
					// $room_font,	
					// $timedate);	
			// }
		
		$a=$i;
		$i=$a+20;
		$c++;		
	}	

	
	
//Easter Egg dates
								




//load data from specific mac adress folder //$mac="18:fe:34:d6:1c:7e"; MAC Adresse von -1.31V Display microC    // für Testzwecke

$foldername = str_replace(':','',$xmac);
//$foldername ="1";

$path = "./data/" . $foldername;
$file = $path . "/data.txt";

$string = file_get_contents($file);	
$configContentJsonObject = json_decode($string);

$modus				= intval($configContentJsonObject->{'mode'});		//choose template

/*
############################################
			make template
	--- do not change code here!!!! --
############################################
*/
				


	

$path = "./data/" . $foldername;
$fileimg = $path . "/modus";
$fileimg2= $fileimg. $modus;
$fileimg3 = $fileimg2 . ".png" ;



//echo $fileimg3;
				
					$img						= imagecreatefrompng($fileimg3);

					$white = imagecolorallocate( $img, 255, 255, 255 );
					$black = imagecolorallocate( $img,   0,   0,   0 );


	
	
// Eink header
 printf("%c", 0x01);
 printf("%c", $refresh&0xff);
 printf("%c", ($refresh>>8)&0xff);
 printf("%c", 0x00);

// Eink data
for ($y=0; $y<$height; $y++)
 {
  for ($x=0; $x<$width; $x+=8)
   {
    $b=0;
    for ($z=0; $z<8; $z++)
     {
      $b<<=1;
      $c=imagecolorat($img, $x+$z, $y);
      if ((($c)&0xff)<0x80) $b|=1;
     }
	 
	 if ($debug == 0) 
		{
		    printf("%c", $b);
		}

   }
 }
imagedestroy($img);



?>