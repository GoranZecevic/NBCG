<?php 
	$host = "localhost";
	$user = "root";
	$pass = "";
	$db = "localdbnbcg";
	
	$con = mysqli_connect($host, $user, $pass, $db);
	/* mysqli_query("SET NAMES 'utf8'"); */
	
	$query = "SELECT naslov, fajl FROM `objektimedia` WHERE odobreno=1 AND tip = 'fajl' AND folder = 59 ORDER BY poslednjaizmjena DESC";
	
	$result = mysqli_query($con, $query);
	
	$response = array();
	
	while($row = mysqli_fetch_array($result)){
		$row = array_map('utf8_decode', $row);
		array_push($response, array("NASLOV"=>$row['naslov'],
									"FAJL"=>$row['fajl']														
									));									
	}
	mysqli_close($con);
	
	echo json_encode(array('server_response'=>$response));