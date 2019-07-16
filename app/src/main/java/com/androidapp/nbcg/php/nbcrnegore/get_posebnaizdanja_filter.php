<?php 
	$host = "localhost";
	$user = "root";
	$pass = "";
	$db = "localdbnbcg";
	
	$con = mysqli_connect($host, $user, $pass, $db);
	/* mysqli_query("SET NAMES 'utf8'"); */
	
	$query = "SELECT izdanja.id, izdanja.datumod, izdanja.naslov, izdanja.opis, izdanja.tekst, izdanja.link, izdanja.cijena, tipovi_izdanja.naslov AS tipovi_naslov, objektimedia.fajl 
				FROM izdanja 
				INNER JOIN objektimedia ON izdanja.id = objektimedia.folder 
				INNER JOIN tipovi_izdanja ON izdanja.tip_novosti = tipovi_izdanja.id 
				WHERE izdanja.tip_novosti = 2 && izdanja.aktivna = 1 && izdanja.odobreno = 1 
						AND objektimedia.odobreno = 1 AND objektimedia.tip = 'slika' AND (objektimedia.sort = 0 OR objektimedia.sort = 100) 
				ORDER BY datumod DESC";
	
	$result = mysqli_query($con, $query);
	
	$response = array();
	
	while($row = mysqli_fetch_array($result)){
		$row = array_map('utf8_decode', $row);
		array_push($response, array("ID"=>$row['id'],
									"DATUMOD"=>$row['datumod'],
									"NASLOV"=>$row['naslov'],
									"OPIS"=>$row['opis'],
									"TEKST"=>$row['tekst'],
									"LINK"=>$row['link'],															
									"CIJENA"=>$row['cijena'],															
									"TIPOVI_NASLOV"=>$row['tipovi_naslov'],
									"FAJL"=>$row['fajl']														
									));									
	}
	mysqli_close($con);
	
	echo json_encode(array('server_response'=>$response));