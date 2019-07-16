<?php 
	$host = "localhost";
	$user = "root";
	$pass = "";
	$db = "localdbnbcg";
	
	$con = mysqli_connect($host, $user, $pass, $db);
	/* mysqli_query("SET NAMES 'utf8'"); */
	
	$query = "SELECT id, naslov, fajl FROM `objektimedia` 
				WHERE folder = 40 AND tip = 'fajl' AND odobreno = 1 
				ORDER BY napravljena DESC";
	
	$result = mysqli_query($con, $query);
	
	$response = array();
	
	while($row = mysqli_fetch_array($result)){
		$row = array_map('utf8_decode', $row);
		array_push($response, array("ID"=>$row['id'],
									"NASLOV"=>$row['naslov'],
									"FAJL"=>$row['fajl']														
									));									
	}
	mysqli_close($con);
	
	echo json_encode(array('server_response'=>$response));