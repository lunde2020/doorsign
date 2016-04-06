<?php
header("Location: http://lundeallin/pixelserver/pixelserver.php");
 
 ######################################################################
 ##################  Hier wird der Spannungsverlauf aufgenommen und gespeichert
 

 /* pChart library inclusions */ 
 include("libs/pChart2.1.4/class/pData.class.php"); 
 include("libs/pChart2.1.4/class/pDraw.class.php"); 
 include("libs/pChart2.1.4/class/pImage.class.php"); 

 //set bat position
$bat_x=7;
$bat_y=5;
$bat_height=30;
// define text syncdate
$date_height = 15;   								// in pixels
$date_buffer = $bat_x; 								// pixel von linken Rand
$date_font ='./fonts/AGENCYB.TTF';

$width = 800;
$height = 800;
 
 
 
require_once('libs/csv_in_array.php');

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

	
$foldername = str_replace(':','',$xmac);
//$foldername ="18fe34d6a261";

$path = "./data/" . $foldername;
$file = $path . "/data.txt";




$voltagedatacsv = $path . "/voltage.csv";	
$csvdata = csv_in_array($voltagedatacsv, ",", "\"", FALSE ); 
$i_max=count($csvdata);
$steps=$i_max/1200;
$steps=ceil($steps);


if ($steps<1)
{
	// $templatepicturestring		= "./templates/white.png";
	// $img						= imagecreatefrompng($templatepicturestring);
	// imagepng ( $img, "voltage.png" , 100);		//saves Image to Server
	exit();
  }	
	
$csvdata_values= array(); 

$a=0;

for($i=0;$i<$i_max;$i=$i+$steps) { 
 $csvdata_values [$i] = $csvdata[$i][2];
 $a=$i;}
 
 

 $csvdata_point= array(); 
$i=0;
for($i;$i<$i_max;$i=$i+$steps) { 
$csvdata_point [$i] = "";}




$date=substr ($csvdata[0][1],0,13);
   $csvdata_point [0] = $date;
 $csvdata_point[$a]="heute"; 
  
 
 

  
 // // // ----- view ------ 
// echo "<pre>\r\n"; 
// print_r($csvdata);
// echo "</pre>\r\n"; 
// // // -----------------

 // // // ----- view ------ 
// echo "<pre>\r\n"; 
// print_r($i_max);
// echo "</pre>\r\n"; 
// // // -----------------

 // // // ----- view ------ 
// echo "<pre>\r\n"; 
// print_r($csvdata_values);
// echo "</pre>\r\n"; 
// // // -----------------
  // // // ----- view ------ 
// echo "<pre>\r\n"; 
// print_r($csvdata_point);
// echo "</pre>\r\n"; 
// // -----------------

  // // // ----- view ------ 
// echo "<pre>\r\n"; 
// print_r($values);
// echo "</pre>\r\n"; 
// // // -----------------

  // // // ----- view ------ 
// echo "<pre>\r\n"; 
// print_r($last);
// echo "</pre>\r\n"; 
// // // -----------------

//=============================================================================================
 
 // $MyData = new pData();   
 // for($i=0;$i<=30;$i++) { $MyData->addPoints(rand(1,15),"Probe 1"); } 
 // $MyData->setSerieTicks("Probe 2",4); 
 // $MyData->setAxisName(0,"Temperatures"); 
 
 /* Create and populate the pData object */ 
 $MyData = new pData();   
 
$MyData->addPoints($csvdata_values,"Temperature");
$MyData->addPoints($csvdata_point,"Labels");
$MyData->setSerieDescription("Labels","Months");
$MyData->setAbscissa("Labels");	  
	  
	  
 
 $MyData->setSerieTicks("Probe 1",4); 
 $MyData->setAxisName(0,"Spanung in [mV]"); 

 /* Create the pChart object */ 
 $myPicture = new pImage(800,500,$MyData); 

 /* Turn of Antialiasing */ 
 $myPicture->Antialias = FALSE; 

 /* Add a border to the picture */ 
 //$myPicture->drawGradientArea(0,0,700,230,DIRECTION_VERTICAL,array("StartR"=>240,"StartG"=>240,"StartB"=>240,"EndR"=>180,"EndG"=>180,"EndB"=>180,"Alpha"=>100)); 
 //$myPicture->drawGradientArea(0,0,700,230,DIRECTION_HORIZONTAL,array("StartR"=>240,"StartG"=>240,"StartB"=>240,"EndR"=>180,"EndG"=>180,"EndB"=>180,"Alpha"=>20)); 

 /* Add a border to the picture */ 
 //$myPicture->drawRectangle(0,0,799,399,array("R"=>0,"G"=>0,"B"=>0)); 
  
 /* Write the chart title */  
 $myPicture->setFontProperties(array("FontName"=>"libs/pChart2.1.4/fonts/Forgotte.ttf","FontSize"=>11)); 
 $myPicture->drawText(150,35,"Spannungsverlauf seit Ladung",array("FontSize"=>20,"Align"=>TEXT_ALIGN_BOTTOMMIDDLE)); 

 /* Set the default font */ 
 $myPicture->setFontProperties(array("FontName"=>"./fonts/arial.TTF","FontSize"=>10)); 

 /* Define the chart area */ 
 $myPicture->setGraphArea(60,40,750,480); 

 /* Draw the scale */ 
 $scaleSettings = array("XMargin"=>10,"YMargin"=>10,"Floating"=>TRUE,"GridR"=>200,"GridG"=>200,"GridB"=>200,"GridAlpha"=>100,"DrawSubTicks"=>TRUE,"CycleBackground"=>TRUE);
 $myPicture->drawScale($scaleSettings); 

 /* Write the chart legend */ 
 //$myPicture->drawLegend(640,20,array("Style"=>LEGEND_NOBORDER,"Mode"=>LEGEND_HORIZONTAL)); 

 /* Turn on Antialiasing */ 
 $myPicture->Antialias = TRUE; 

 /* Enable shadow computing */ 
 //$myPicture->setShadow(TRUE,array("X"=>1,"Y"=>1,"R"=>0,"G"=>0,"B"=>0,"Alpha"=>10)); 

 /* Draw the area chart */ 
 //$Threshold = ""; 
// $Threshold[] = array("Min"=>0,"Max"=>5,"R"=>187,"G"=>220,"B"=>0,"Alpha"=>100); 
//$Threshold[] = array("Min"=>5,"Max"=>10,"R"=>240,"G"=>132,"B"=>20,"Alpha"=>100); 
// $Threshold[] = array("Min"=>10,"Max"=>20,"R"=>240,"G"=>91,"B"=>20,"Alpha"=>100); 
 //$myPicture->setShadow(TRUE,array("X"=>1,"Y"=>1,"R"=>0,"G"=>0,"B"=>0,"Alpha"=>20)); 
 //$myPicture->drawAreaChart(array("Threshold"=>$Threshold)); 

 /* Draw a line chart over */ 
 $myPicture->drawLineChart(array("ForceColor"=>TRUE,"ForceR"=>0,"ForceG"=>0,"ForceB"=>0)); 

 /* Draw a plot chart over */ 
 //$myPicture->drawPlotChart(array("PlotBorder"=>TRUE,"BorderSize"=>1,"Surrounding"=>-255,"BorderAlpha"=>80)); 

 /* Write the thresholds */ 
 $myPicture->drawThreshold(3000,array("WriteCaption"=>FALSE,"Caption"=>"max","Alpha"=>70,"Ticks"=>2,"R"=>0,"G"=>0,"B"=>255)); 
 $myPicture->drawThreshold(4250,array("WriteCaption"=>FALSE,"Caption"=>"min","Alpha"=>70,"Ticks"=>2,"R"=>0,"G"=>0,"B"=>255)); 
 //$myPicture->drawThreshold(10,array("WriteCaption"=>TRUE,"Caption"=>"Error Zone","Alpha"=>70,"Ticks"=>2,"R"=>0,"G"=>0,"B"=>255)); 
   
 /* Render the picture (choose the best way) */ 
 //imagejpeg ( $myPicture, "myPicture.jpg" , 100);		//saves Image to Server
 
  
 
$voltagedatapng = $path . "/modus1_temp.png"; 
 
$myPicture->render($voltagedatapng);
 
 
 
$templatepicturestring		= "./templates/white.png";
$img						= imagecreatefrompng($templatepicturestring);



$white = imagecolorallocate( $img, 255, 255, 255 );
$black = imagecolorallocate( $img,   0,   0,   0 );

					 battery_load (	$xbatterymv,
									$img,
									$bat_x,
									$bat_y,
									$bat_height);
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
						
						
					$path = "./data/" . $foldername;
					$file = $path . "/modus1_temp.png";		
					$src_image	= imagecreatefrompng($file);	
					imagecopyresized ( 
					$img, 
					$src_image, 
					0, 
					60, 
					0, 
					0, 
					800, 
					500, 
					800, 
					500);	
 
 
 //$voltagedatapng = $path . "/modus1.png"; 
 
//$img->render($voltagedatapng);
 
 $path = "./data/" . $foldername;
$file = $path . "/modus1.png";	
 imagepng ( $img, $file , 0);	
 

// debug ==> picture debug... show image


		// header('Content-Type: image/png');
		// imagejpeg ( $myPicture);

//$myPicture->autoOutput("pictures/example.drawAreaChart.threshold.png"); 


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

?>