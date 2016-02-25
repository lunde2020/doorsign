<?php
header("Location: http://lundeallin/pixelserver/pixelserver.php");
 
 ######################################################################
 ##################  Hier wird der Spannungsverlauf aufgenommen und gespeichert
 
 
 

 /* pChart library inclusions */ 
 include("libs/pChart2.1.4/class/pData.class.php"); 
 include("libs/pChart2.1.4/class/pDraw.class.php"); 
 include("libs/pChart2.1.4/class/pImage.class.php"); 

 
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
$path = "./data/" . $foldername;
//save data to specific mac adress
$voltagedatacsv = $path . "/voltage.csv";	
	
	
if ($xbatterymv>2000 && $xbatterymv<5000)			// falls von localhost zugegriffen wird -> keine Batterispannung
	
{
		$line=array (date("r"), $xbatterymv);
		$handle = fopen($voltagedatacsv, "a");		// Then add your line (fputcsv­Docs):
		fputcsv($handle, $line); 					// $line is an array of string values here
		fclose($handle);							//Then close the handle (fclose­Docs):
  }
 
//$voltagedatacsv="data/voltage.csv";
require_once('libs/csv_in_array.php');
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

$voltagedatapng = $path . "/voltage.png"; 
 
$myPicture->render($voltagedatapng);
// debug ==> picture debug... show image


		// header('Content-Type: image/png');
		// imagejpeg ( $myPicture);

//$myPicture->autoOutput("pictures/example.drawAreaChart.threshold.png"); 
?>