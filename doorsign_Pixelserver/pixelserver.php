<?php
//header('Content-Type: image/gif');

/*srand(time());
$i=rand()%2;
if ($i==0) $fn="apconnect.png";
if ($i==1) $fn="batempty.png";
*/


$xml=simplexml_load_file("config.xml") or die("Error: Cannot create object");
$test = $xml->book[1]->title; 




//$fn="http://lundeallin/neterror2.png";

$img=imagecreatefrompng('neterror2.png');


//define display size
$width = 800;
$height = 600;

// Refresh time in sec
$refresh = 65000;

// define text "date"
$date_height = 15;   // in pixels
$date_bufffer = 10; // pixel von linken Rand
$date_font ='./fonts/AGENCYB.TTF';

//define text "name"
$name_x=115; 
$name_y=60;    		//unten Links Ecke
$name_width=470;
$name_height=30;
$name_font='./fonts/AGENCYB.TTF';
$name_text="Prof. Dr.-Ing. habil. Catherina Burghart";

//define text "roll"
$roll_x=230; 
$roll_y=105;  		//unten Links Ecke
$roll_width=400;
$roll_height=20;
$roll_font='./fonts/AGENCYB.TTF';
$roll_text="Professorin für Angewandte Informatik";
$roll_text=$test;
//define text "contact_phone"
$contact_phone_x=475; 
$contact_phone_y=350;    		//unten Links Ecke
$contact_phone_width=400;
$contact_phone_height=15;
$contact_phone_font='./fonts/arial.TTF';
$contact_phone_text="Tel. (0721) 925-1868";

//define text "contact_email"
$contact_email_x=$contact_phone_x; 
$contact_email_y=$contact_phone_y+30;    		//unten Links Ecke
$contact_email_width=400;
$contact_email_height=15;
$contact_email_font='./fonts/arial.TTF';
$contact_email_text="catherina.burghart@hs-karlsruhe.de";

//define text "times"
$times_x=$contact_phone_x; 
$times_y=450;    		//unten Links Ecke
$times_width=400;
$times_height=15;
$times_font='./fonts/arial.TTF';
$times_text="Do. 16.00 - 17.00 Uhr  \n& nach Vereinbarung";


//define text "room"
$room_x=465; 
$room_y=250;    		//unten Links Ecke
$room_width=400;
$room_height=75;
$room_font='./fonts/arial.TTF';
$room_text="F-203";

//define text "status"
$status_x=50;
$status_y=$contact_phone_y-100;  		//unten Links Ecke
$status_width=350;
$status_height=15;
$status_font='./fonts/arial.TTF';
$status_text="bin gerade leider im Urlaub =)";

//define text "information"
$information_x=50;
$information_y=350;  		//unten Links Ecke
$information_width=400;
$information_height=15;
$information_font='./fonts/arial.TTF';
$information_text="Fachgebiete: Informatik, Softwaretechnik, Angewandte Mathematik\nHallo, nun möchte ich diese Statusmeldung mal genauer ansehen!";

//wrap status und Informationen für Textumbruch

$status_text_wraped = wordwrap ($status_text, 20, "\n", true);
$information_text_wraped = wordwrap ($information_text, 30, "\n", true);

// //underline text ==> dfunktionier nicht richtig

// $e = explode(' ', $times_text);

// for($i=0;$i<count($e);$i++) {
  // $e[$i] = implode('&#x0332;', str_split($e[$i]));
// }

//$times_text = implode(' ', $e);

//$img = imagecreatetruecolor( $width, $height );

// Colors
$white = imagecolorallocate( $img, 255, 255, 255 );
$black = imagecolorallocate( $img,   0,   0,   0 );

imagefill( $img, 0, 0, $white );

// imageline($img, 0,0, 0,$height-10, $black);
// imageline($img, 0,$height-10, $width-10,$height-10, $black);

 //imageline($img, 0,$height-300, $width-10,$height-10, $black);
 //imageline($img, 0,$height-400, $width-10,$height-10, $black);
 //imageline($img, 0,$height-600, $width-10,$height-10, $black);


//$box = @imageTTFBbox($size,0,$font,$text);
//$width = abs($box[4] - $box[0]);
//$height = abs($box[5] - $box[1]);





//imagestring($img, 5, 350, 0, date("r"), $black);







// $box = @imageTTFBbox($loadedheight_name,0,$font,$text);
// $width = abs($box[4] - $box[0]);
// $height = abs($box[5] - $box[1]);


//write name
imagettftext($img,
    $name_height,				//SIze
    0,					//angele
    $name_x, 				// x von oberer linker ecke
	$name_y,					// y von oberer linker ecke
    $black,				// colour
    $name_font,	// font
    $name_text);		// text

//write roll	
imagettftext($img,
    $roll_height,				//SIze
    0,					//angele
    $roll_x, 				// x von oberer linker ecke
	$roll_y,					// y von oberer linker ecke
    $black,				// colour
    $roll_font,	// font
    $roll_text);		// text	
	
//write contact_phone	
imagettftext($img,
    $contact_phone_height,				//SIze
    0,					//angele
    $contact_phone_x, 				// x von oberer linker ecke
	$contact_phone_y,					// y von oberer linker ecke
    $black,				// colour
    $contact_phone_font,	// font
    $contact_phone_text);		// text		
	
//write contact_email	
imagettftext($img,
    $contact_email_height,				//SIze
    0,					//angele
    $contact_email_x, 				// x von oberer linker ecke
	$contact_email_y,					// y von oberer linker ecke
    $black,				// colour
    $contact_email_font,	// font
    $contact_email_text);		// text	

	
//write times
imagettftext($img,
    $times_height,				//SIze
    0,					//angele
    $times_x, 				// x von oberer linker ecke
	$times_y,					// y von oberer linker ecke
    $black,				// colour
    $times_font,	// font
    $times_text);		// text	
	
	
//write room
imagettftext($img,
    $room_height,				//SIze
    0,					//angele
    $room_x, 				// x von oberer linker ecke
	$room_y,					// y von oberer linker ecke
    $black,				// colour
    $room_font,	// font
    $room_text);		// text	
	
//write status
imagettftext($img,
    $status_height,				//SIze
    0,					//angele
    $status_x, 				// x von oberer linker ecke
	$status_y,					// y von oberer linker ecke
    $black,				// colour
    $status_font,	// font
    $status_text_wraped);		// text	
	
imagettftext($img,
    $information_height,				//SIze
    0,					//angele
    $information_x, 				// x von oberer linker ecke
	$information_y,					// y von oberer linker ecke
    $black,				// colour
    $information_font,	// font
    $information_text_wraped);		// text		
	
//write date
$date_temp=date("r");
$date=substr ($date_temp,0,16);
$time=substr ($date_temp,17,5);

// $tb = imagettfbbox(17, 0, 'airlock.ttf', 'Hello world!');

// $box = imageTTFBbox($size_date,0,$font_date, $date);
// $temp_width = abs($box[4] - $box[0]);
// $temp_height = abs($box[5] - $box[1]);	
	

// $tb = imagettfbbox(17, 0, './fonts/AGENCYB.TTF', 'Hello world!');	
	// $x = ceil((width - $tb[2]) / 2); // lower left X coordinate for text
// imagettftext($im, 17, 0, $x, 400, $black, './fonts/AGENCYB.TTF', 'Hello world!'); // write text to image


imagettftext($img,
    $date_height,			//SIze
    0,					//angle	
    675, 				// x von oberer linker ecke
	20,					// y von oberer linker ecke
    $black,				// colour
    $date_font,			// font
    $date);				// text
	
imagettftext($img,
    $date_height,			//SIze
    0,					//angle	
    745, 				// x von oberer linker ecke
	40,					// y von oberer linker ecke
    $black,				// colour
    $date_font,			// font
    $time);				// text
	

	
imagejpeg ( $img, "simpleexample.jpg" , 100);

header('Content-Type: application/octet-stream');
header('Content-Disposition: attachment; filename="image.bm"');


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
    printf("%c", $b);
   }
 }
imagedestroy($img);




// bis hier Malte Code und auch aus Beispiel


/*
//fpassthru(fopen("http://localhost:8080/?http://meuk.spritesserver.nl/eink/", "r"));
//fpassthru(fopen("einkimg.bin", "r"));
//header("Location: http://spritesmods.com:8266/?http://meuk.spritesserver.nl/eink/");
//exit(0);

function genVoorspellingImg($lat, $lon) {
//api.openweathermap.org/data/2.5/weather?lat=35&lon=139
}

function genNeerslagImg($lat, $lon) {
    $w=250; $h=250;
    $data=explode("\n", file_get_contents("http://gps.buienradar.nl/getrr.php?lat=$lat&lon=$lon"));
    $im=imagecreate($w,$h);
    $wh=imagecolorallocate($im, 255, 255, 255);
    $b=imagecolorallocate($im, 0, 0, 0);
    imageline($im, 0,0, 0,$h-1, $b);
    imageline($im, 0,$h-1, $w-1,$h-1, $b);
    $last=0; $x=0; $oldx=0;
    foreach ($data as $d) {
        $p=explode("|", chop($d));
        imageline($im, $oldx, $h-(($last*$h)/255), $x, $h-(($p[0]*$h)/255), $b);
        if (substr($p[1], -2)=="00") {
            imageline($im, $x, 0, $x, $h-1, $b);
        }
        if (substr($p[1], -2)=="30") {
            imageline($im, $x, $h/2, $x, $h-1, $b);
        }
        $oldx=$x;
        $x+=($w/count($data));
        $last=$p[0];
    }
    return $im;
}

/*
srand(time());
$i=rand()%5;
if ($i==0) $fn="799px-De_re_metallica_1556-033.png";
if ($i==1) $fn="corazon_law.png";
if ($i==2) $fn="chicken.png";
if ($i==3) $fn="test.png";
if ($i==4) $fn="bsa_jillr.png";
$im=imagecreatefrompng($fn);


$im=imagecreatetruecolor(800, 600);


$w=imagecolorallocate($im, 255, 255, 255);
$b=imagecolorallocate($im, 0, 0, 0);

imagefilledrectangle($im, 0,0,imagesx($im),imagesy($im),$w);
imagestring($im, 5, 350, 0, date("r"), $b);

imagettftext($im,
    210,
    0,
    0, 230,
    $b,
    "/usr/share/fonts/truetype/ttf-dejavu/DejaVuSans.ttf",
    date("H:i"));

$rss=simplexml_load_file("http://www.nu.nl/rss/Algemeen");
$y=230+40;
foreach ($rss->channel->item as $item) {
//    var_dump($item->title);
    imagettftext($im,
        28,
        0,
        0, $y,
        $b,
        "/usr/share/fonts/truetype/ttf-dejavu/DejaVuSans.ttf",
        $item->title);
    $y+=40;
}

//$n=genNeerslagImg("52.25741", "6.79277");
//imagecopy($im, $n, 0, 250, 0,0, imagesx($n), imagesy($n));

if (isset($_REQUEST["png"])) {
    header("Content-Type: image/png");
    imagepng($im);
    exit(0);
}

header("Content-Type: application/x-esp-eink");
printf("%c", 0);
for ($y=0; $y<600; $y++) {
    for ($x=0; $x<800; $x+=8) {
        $b=0;
        for ($z=0; $z<8; $z++) {
            $b<<=1;
            $c=imagecolorat($im, $x+$z, $y);
            if ((($c)&0xff)<0x80) $b|=1;
        }
        printf("%c", $b);
        $data.=chr($b);
    }
}
*/
?>