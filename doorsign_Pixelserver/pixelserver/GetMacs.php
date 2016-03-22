<?php

$macsArray = array();
if ($handle = opendir('./data')) {
    /* Das ist der korrekte Weg, ein Verzeichnis zu durchlaufen. */
	$i = 0;
    while (false !== ($entry = readdir($handle))) {
		if ((strlen($entry) == 12) && is_dir("./data/".$entry)) {
			$macsArray[$i++] = $entry;
		} 
    }

    /* Dies ist der FALSCHE Weg, ein Verzeichnis zu durchlaufen. */
    while ($entry = readdir($handle)) {
        echo "$entry\n";
    }

    closedir($handle);
	
	echo json_encode($macsArray);
}

?>