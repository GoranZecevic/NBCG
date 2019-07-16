<?php 
	$host = "localhost";
	$user = "root";
	$pass = "";
	$db = "localdbnbcg";
	
	$con = mysqli_connect($host, $user, $pass, $db);
	mysqli_set_charset($con, "utf8") or die(mysqli_error($con));
	/* echo mysqli_character_set_name($con); */
	
	$query = "SELECT vijesti.id, vijesti.datumod, vijesti.naslov, vijesti.opis, vijesti.description, tipovi_novosti.naslov as tip_novosti, vijesti.link, objektimedia.fajl 
				FROM vijesti 
				INNER JOIN objektimedia ON vijesti.id = objektimedia.folder 
				INNER JOIN tipovi_novosti ON vijesti.tip_novosti = tipovi_novosti.id 
				WHERE vijesti.tip_novosti = 8 AND vijesti.aktivna = 1 AND vijesti.odobreno = 1 AND objektimedia.odobreno = 1 AND (objektimedia.sort = 0 OR objektimedia.sort = 100) 
				GROUP BY vijesti.id ORDER BY vijesti.datumod DESC";
	
	$result = mysqli_query($con, $query);
	
	$response = array();
	
	while($row = mysqli_fetch_array($result)){
		$row = array_map('utf8_encode', $row);
		array_push($response, array("ID"=>$row['id'],
									"DATUMOD"=>$row['datumod'],
									"NASLOV"=>$row['naslov'],
									"OPIS"=>$row['opis'],
									"DESCRIPTION"=>$row['description'],
									"TIP_NOVOSTI"=>$row['tip_novosti'],
									"LINK"=>$row['link'],
									"FAJL"=>$row['fajl']
								));									
	}
	mysqli_close($con);
	
	echo json_encode(array('server_response'=>$response));