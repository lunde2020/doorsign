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

$c=0;	
foreach (getallheaders() as $name => $value) 
	{ 
		 if ($c==3)
			{
				$xmac = $value;
				$c++;
			}
	}

//$xmac=""18:fe:34:d6:1c:7d"; //Beck
	
// Easter Egg Random
/*srand(time());
$i=rand()%2;=1
if ($i==0) $fn="apconnect.png";
if ($i=) $fn="batempty.png";
*/



// DEBUG option
$debug		= 0;				
					// 0->"DisplayMode"		==>	choose this for operation mode!			
					// 1->"Picture-Debug" 	==>	show picture in Browser 						
					// 2->"PHP-Debug"		==>	show debug comments -> empty page no errors	
					
// show PHP-Header
$php_header	= 0;				
					// 0->"don't show"		
					// 1->"show" 							
			

//define display size
$width 		= 800;
$height 	= 600;	

// Refresh time in sec
$refresh 	= 0;	
					// 0->"NoWakeUp"		==> choose this for only wake up by reset
					// 3600->"1Hour"		==> wakeup each hour
					
//Easter Egg dates

//choose contact

$contact=1;
					//0 --> Beck
					//1 --> Artinger
					//2 --> Burghart
					
$xml		= simplexml_load_file("./data/persons2.xml") or die("Error: Cannot create object");
$contactid = $xml->xpath('//quote[mac=18:fe:34:d6:1c:7e]/id/text()');
$contact = intval (reset ($contactid));
$contact=1;
$name_text 			= $xml->person[$contact]->name; 
$roll_text 			= $xml->person[$contact]->roll; 
$room_text 			= $xml->person[$contact]->room; 
$tel_text 			= $xml->person[$contact]->tel; 
$email_text 		= $xml->person[$contact]->email; 
$times_text 		= $xml->person[$contact]->times; 
$status_text 		= $xml->person[$contact]->status; 
$information_text 	= $xml->person[$contact]->information; 
$times_text 		= $xml->person[$contact]->times; 

// // Choose template Picture
// $template					= 1;
// $templatestring 			= (string)$template;
// $templatepicturestring		= "./templates/{$templatestring}.png";
// $img						= imagecreatefrompng($templatepicturestring);

// //choose template
// $xml		= simplexml_load_file("./templates/templates.xml") or die("Error: Cannot create object");
// $contact = intval (reset ($xml->xpath('//quote[mac=$template]/id/text()')));

// $tag1_text=$status_text;
// $tag1_text_font='./fonts/BRUSHSCI.TTF';
// $tag1_text_height=23;
// $tag1_text_wrap=15;

//imagefill( $img, 0, 0, $white ); // falls am Ende gar keine Vorlage mehr gibt

// define colors
$white = imagecolorallocate( $img, 255, 255, 255 );
$black = imagecolorallocate( $img,   0,   0,   0 );

//set bat position
$bat_x=7;
$bat_y=5;
$bat_height=30;
				
// define underline imagegettfttext_centered_underlined
$ydistance_centered = 5;						
$xdistanceLR_centered=8;			// think 0 is fine
$yheightlineLR_centered=10;
$centerCorrecter_centered=0;
// define underline imagegettfttext_xy_underlined
$ydistance_xy=2;
$xdistanceLR_xy=0;
$yheightlineLR_xy=0;

// define text syncdate
$date_height = 15;   								// in pixels
$date_buffer = $bat_x; 								// pixel von linken Rand
$date_font ='./fonts/AGENCYB.TTF';

//define text "room"
$room_y=250;  
$room_x=460;  		
$room_width=400;
$room_height=75;
$room_font='./fonts/arial.TTF';
//$room_text="F-203";

//define text "name"
$name_y=75;    						
$name_height=30;
$name_underline_thickness=2;
$name_font='./fonts/AGENCYB.TTF';

//define text "roll"
$roll_y=$name_y+45;  							//unten Links Ecke von Text
$roll_width=400;
$roll_height=20;
$roll_underline_thickness=2;
$roll_font='./fonts/AGENCYB.TTF';

$tags_tag_font='./fonts/arialbi.ttf';
$tags_tag_underline_thickness=1;
$tags_tag_height=17;

$tags_text_height=15;
$tags_text_font='./fonts/arial.TTF';
$deltadefinetags_x=15;
$deltadefinetags_y=30;
$tags_text_wrap=30;

//TAG1
$tag1_tag="";
$tag1_tag_font=$tags_tag_font;
$tag1_tag_height=$tags_tag_height;
$tag1_underline_thickness=$tags_tag_underline_thickness;
$tag1_x=450;
$tag1_y=310;

$tag1_text=$status_text;
$tag1_text_font='./fonts/BRUSHSCI.TTF';
$tag1_text_height=23;
$tag1_text_wrap=15;

//TAG2
$tag2_tag="Fachgebiete:";
$tag2_tag_font=$tags_tag_font;
$tag2_tag_height=$tags_tag_height;
$tag2_underline_thickness=$tags_tag_underline_thickness;
$tag2_x=35;
$tag2_y=400;

$tag2_text=$information_text;
$tag2_text_font=$tags_text_font;
$tag2_text_height=$tags_text_height;
$tag2_text_wrap=30;

//TAG3
$tag3_tag="Kontakt:";
$tag3_tag_font=$tags_tag_font;
$tag3_tag_height=$tags_tag_height;
$tag3_underline_thickness=$tags_tag_underline_thickness;
$tag3_x=35;
$tag3_y=200;
$tag3_text="{$tel_text}\n{$email_text}";	
$tag3_text_font=$tags_text_font;
$tag3_text_height=$tags_text_height;
$tag3_text_wrap=40;

//TAG4
$tag4_tag="Sprechzeiten:";
$tag4_tag_font=$tags_tag_font;
$tag4_tag_height=$tags_tag_height;
$tag4_underline_thickness=$tags_tag_underline_thickness;
$tag4_x=35;
$tag4_y=300;

//$tag4_text=$times_text;
$tag4_text=$times_text;
$tag4_text_font=$tags_text_font;
$tag4_text_height=$tags_text_height;			
$tag4_text_wrap=40;
$findMich="Uhr";
$pos = strpos($times_text, $findMich);
if ($pos === false) {
	$tag4_text_wraped = wordwrap ($tag4_text, $tag4_text_wrap, "\n", true);
    $tag4_text=$tag4_text_wraped;
} else {
	$timesub1=substr ($times_text,0,$pos+3);
	$timesub2=substr ($times_text,$pos+4);
	$tag4_text="{$timesub1}\n{$timesub2}";
}

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
		
		if ($php_header==1)
			{
				imagettftext($img,
					20,			
					0,					
					20, 				
					50+$i,					
					$black,				
					$room_font,	
					$timedate);	
			}
		
		$a=$i;
		$i=$a+20;
		$c++;
	}
 
/*
############################################
			make template
	--- do not change code here!!!! --
############################################
*/

battery_load (	$xbatterymv,
				$img,
				$bat_x,
				$bat_y,
				$bat_height);
				
// write tag1
imagegettfttext_xy_underlined (				
		$img, 						
		$tag1_x, 				
		$tag1_y, 				
		$tag1_tag_height, 			
		$tag1_tag_font, 				
		$tag1_tag,						
		$black,
		$ydistance_xy, 
		$xdistanceLR_xy, 
		$yheightlineLR_xy,
		$tag1_underline_thickness);

$tag1_text_wraped = wordwrap ($tag1_text, $tag1_text_wrap, "\n", true);
imagettftext($img,
    $tag1_text_height,				
    0,					
    $tag1_x+$deltadefinetags_x, 				
	$tag1_y+$deltadefinetags_y,					
    $black,				
    $tag1_text_font,	
    $tag1_text_wraped);
	
// write tag2
imagegettfttext_xy_underlined (				
		$img, 						
		$tag2_x, 				
		$tag2_y, 				
		$tag2_tag_height, 			
		$tag2_tag_font, 				
		$tag2_tag,						
		$black,
		$ydistance_xy, 
		$xdistanceLR_xy, 
		$yheightlineLR_xy,
		$tag2_underline_thickness);

$tag2_text_wraped = wordwrap ($tag2_text, $tag2_text_wrap, "\n", true);
imagettftext($img,
    $tag2_text_height,				
    0,					
    $tag2_x+$deltadefinetags_x, 				
	$tag2_y+$deltadefinetags_y,					
    $black,				
    $tag2_text_font,	
    $tag2_text_wraped);

// write tag3
imagegettfttext_xy_underlined (				
		$img, 						
		$tag3_x, 				
		$tag3_y, 				
		$tag3_tag_height, 			
		$tag3_tag_font, 				
		$tag3_tag,						
		$black,
		$ydistance_xy, 
		$xdistanceLR_xy, 
		$yheightlineLR_xy,
		$tag3_underline_thickness);

$tag3_text_wraped = wordwrap ($tag3_text, $tag3_text_wrap, "\n", true);
imagettftext($img,
    $tag3_text_height,				
    0,					
    $tag3_x+$deltadefinetags_x, 				
	$tag3_y+$deltadefinetags_y,					
    $black,				
    $tag3_text_font,	
    $tag3_text_wraped);

// write tag4
imagegettfttext_xy_underlined (				
		$img, 						
		$tag4_x, 				
		$tag4_y, 				
		$tag4_tag_height, 			
		$tag4_tag_font, 				
		$tag4_tag,						
		$black,
		$ydistance_xy, 
		$xdistanceLR_xy, 
		$yheightlineLR_xy,
		$tag4_underline_thickness);

$tag4_text_wraped = wordwrap ($tag4_text, $tag4_text_wrap, "\n", true);
imagettftext($img,
    $tag4_text_height,				
    0,					
    $tag4_x+$deltadefinetags_x, 				
	$tag4_y+$deltadefinetags_y,					
    $black,				
    $tag4_text_font,	
    $tag4_text_wraped);


imagegettfttext_centered_underlined (						
		$img, 						
		$name_y, 				
		$name_height, 			
		$name_font, 				
		$name_text,						
		$black,
		$ydistance_centered, 
		$xdistanceLR_centered, 
		$yheightlineLR_centered,
		$centerCorrecter_centered,
		$name_underline_thickness);	
		
//define text "roll"
imagegettfttext_centered_underlined (						
		$img, 						
		$roll_y, 				
		$roll_height, 			
		$roll_font, 				
		$roll_text,						
		$black,
		$ydistance_centered, 
		$xdistanceLR_centered,
		$yheightlineLR_centered,
		$centerCorrecter_centered,
		$roll_underline_thickness);	
	
//write room
imagettftext($img,
    $room_height,			
    0,					
    $room_x, 				
	$room_y,					
    $black,				
    $room_font,	
    $room_text);		
		
	
//write date
$date_temp=date("r");
$date=substr ($date_temp,0,16);
$time=substr ($date_temp,17,5);
$timedate="{$date} {$time}";

$bbox = imagettfbbox($date_height, 0, $date_font, $timedate);
$textwidth =($bbox[2] - $bbox[0]);
imagettftext($img,
    $date_height,			
    0,					
    $width-$date_buffer-$textwidth, 				
	$date_height+5,					
    $black,				
    $date_font,			
    $timedate);				

	

imagejpeg ( $img, "simpleexample.jpg" , 100);		//saves Image to Server


// debug ==> picture debug... show image
if ($debug == 1) 
	{
		header('Content-Type: image/jpeg');
		imagejpeg ( $img);
	}
else
	{
		
		if ($debug == 0) 
		{
			header('Content-Type: application/octet-stream');
			header('Content-Disposition: attachment; filename="image.bm"');
		}
	}

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


/*
############################################
			declar functions
	--- do not change code here!!!! --
############################################
*/

function battery_load (
				$voltage,
				$dst_image,
				$dst_x,
				$dst_y,
				$dst_h)
	{	
	
	$retval=1;
			if ($voltage>3300)
				{
					$batimgstring = './battery/0.png';
				}
			if ($voltage>3500)
				{
					$batimgstring = './battery/25.png';
				}
			if ($voltage>3700)
				{
					$batimgstring = './battery/50.png';
				}	
				
			if ($voltage>3900)
				{
					$batimgstring = './battery/75.png';
				}	
			if ($voltage>4100)
				{
					$batimgstring = './battery/100.png';
				}
				
			if ($voltage<=3300)
				{
					$batimgstring = './battery/0.png';
				}

				
$src_image	= imagecreatefrompng($batimgstring);		
$src_x		= 0;
$src_y		= 0;
$data 		= getimagesize($batimgstring);
$src_w 		= $data[0];
$src_h 		= $data[1];
$value 		= $src_w / $src_h;
$value_new	= round($value);
$dst_w=$value_new*$dst_h;				
$test=imagecopyresized ( 
		$dst_image, 
		$src_image, 
		$dst_x, 
		$dst_y, 
		$src_x, 
		$src_y, 
		$dst_w, 
		$dst_h, 
		$src_w, 
		$src_h);	


		
		
$divider=1000;
$dividedvoltage=$voltage / $divider;	
$roundvoltage=round($dividedvoltage, 2);
$voltage="{$roundvoltage}V";
$roundvoltage=str_replace(".",",",$voltage);


$black = imagecolorallocate( $dst_image,   0,   0,   0 );	
imagettftext($dst_image,
					$dst_h*0.5,			
					0,					
					$dst_w+10, 				
					$dst_y+$dst_h-$dst_h/2,					
					$black,				
					'./fonts/AGENCYB.TTF',	
					$roundvoltage);	
					



		
 return $retval;		
		}

function imagegettfttext_centered_underlined (						

		$img, 						
		$px_text_y, 				
		$px_text_height, 			
		$text_font, 				
		$text,						
		$color,
		$ydistance, 
		$xdistanceLR, 
		$yheightlineLR,
		$centerCorrecter,
		$underline_thickness)		
{				


imagesetthickness ($img, $underline_thickness);
$retval=1;
$bbox = imagettfbbox($px_text_height, 0, $text_font, $text);
$textwidth =($bbox[2] - $bbox[0]);
$center1 = (imagesx($img) / 2) - (($bbox[2] - $bbox[0]) / 2);

imageline(
			$img, 
			$center1-$xdistanceLR,
			$px_text_y+$ydistance, 
			$center1+$textwidth+$xdistanceLR,
			$px_text_y+$ydistance, 
			$color);			
imageline(
			$img, 
			$center1-$xdistanceLR,$px_text_y+$ydistance, 
			$center1-$xdistanceLR,
			$px_text_y-$yheightlineLR+$ydistance, 
			$color);
imageline(
			$img, 
			$center1+$textwidth+$xdistanceLR,
			$px_text_y+$ydistance, 
			$center1+$textwidth+$xdistanceLR,
			$px_text_y-$yheightlineLR+$ydistance, 
			$color);
imagettftext(
			$img,
			$px_text_height,				
			0,					
			$center1-$centerCorrecter,
			$px_text_y,					
			$color,				
			$text_font,	
			$text);		

 return $retval;
}

function imagegettfttext_xy_underlined (						

		$img, 						
		$px_text_x, 				
		$px_text_y, 				
		$px_text_height, 			
		$text_font, 				
		$text,						
		$color,
		$ydistance, 
		$xdistanceLR, 
		$yheightlineLR,
		$underline_thickness)						
{				

imagesetthickness ($img, $underline_thickness);
$retval=1;
$bbox = imagettfbbox($px_text_height, 0, $text_font, $text);
$textwidth =($bbox[2] - $bbox[0]);

imageline(
			$img, 
			$px_text_x-$xdistanceLR,
			$px_text_y+$ydistance, 
			$px_text_x+$textwidth+$xdistanceLR,
			$px_text_y+$ydistance, 
			$color);			
imageline(
			$img, 
			$px_text_x-$xdistanceLR,$px_text_y+$ydistance, 
			$px_text_x-$xdistanceLR,
			$px_text_y-$yheightlineLR+$ydistance, 
			$color);
imageline(
			$img, 
			$px_text_x+$textwidth+$xdistanceLR,
			$px_text_y+$ydistance, 
			$px_text_x+$textwidth+$xdistanceLR,
			$px_text_y-$yheightlineLR+$ydistance, 
			$color);
imagettftext(
			$img,
			$px_text_height,				
			0,					
			$px_text_x,
			$px_text_y,					
			$color,				
			$text_font,	
			$text);		

 return $retval;
}

?>