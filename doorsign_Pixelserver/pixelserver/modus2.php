<?php

//fpassthru(fopen("http://localhost:8080/?http://meuk.spritesserver.nl/eink/", "r"));
//fpassthru(fopen("einkimg.bin", "r"));
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
*/

$im=imagecreatetruecolor(800, 600);


$w=imagecolorallocate($im, 255, 255, 255);
$b=imagecolorallocate($im, 0, 0, 0);

imagefilledrectangle($im, 0,0,imagesx($im),imagesy($im),$w);
//imagestring($im, 5, 350, 0, date("r"), $b);

imagettftext($im,
    210,
    0,
    0, 230,
    $b,
    "./fonts/AGENCYB.TTF",
    date("H:i"));

$rss=simplexml_load_file("http://rss.focus.de/fol/XML/rss_folnews_eilmeldungen.xml");
$y=230+40;

//$tag4_text_wraped = wordwrap ($tag4_text, $tag4_text_wrap, "\n", true);
foreach ($rss->channel->item as $item) {
//    var_dump($item->title);



    imagettftext($im,
        28,
        0,
        0, $y,
        $b,
           "./fonts/AGENCYB.TTF",
        $item->title);
    $y+=40;
}


$foldername = str_replace(':','',$xmac);

$path = "./data/" . $foldername;
$foldername = 1;
$file = $path . "/modus2.png";	
imagepng ( $im, $file , 0);	

//$n=genNeerslagImg("52.25741", "6.79277");
//imagecopy($im, $n, 0, 250, 0,0, imagesx($n), imagesy($n));

// if (isset($_REQUEST["png"])) {
    // header("Content-Type: image/png");
    // imagepng($im);
    // exit(0);
// }

// header("Content-Type: application/x-esp-eink");
// printf("%c", 0);
// for ($y=0; $y<600; $y++) {
    // for ($x=0; $x<800; $x+=8) {
        // $b=0;
        // for ($z=0; $z<8; $z++) {
            // $b<<=1;
            // $c=imagecolorat($im, $x+$z, $y);
            // if ((($c)&0xff)<0x80) $b|=1;
        // }
        // printf("%c", $b);
        // $data.=chr($b);
    // }
// }
?>