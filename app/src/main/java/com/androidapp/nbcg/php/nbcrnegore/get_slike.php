<?php 
	$host = "localhost";
	$user = "root";
	$pass = "";
	$db = "localdbnbcg";
	
	$con = mysqli_connect($host, $user, $pass, $db);
	mysqli_set_charset($con, "utf8") or die(mysqli_error($con));
	/* echo mysqli_character_set_name($con); */
	
	$id = isset($_GET['id']) ? $_GET['id'] : '';
	
	$query = "SELECT fajl, naslov FROM `objektimedia` WHERE folder = '$id'  AND tip = 'slika' ORDER BY sort ASC";
	
	$result = mysqli_query($con, $query);
	
	$response = array();
	
	while($row = mysqli_fetch_array($result)){
		$row = array_map('utf8_encode', $row);
		array_push($response, array("FAJL"=>$row['fajl'],
									"NASLOV"=>$row['naslov']
								));									
	}
	mysqli_close($con);
	
	echo json_encode(array('server_response'=>$response));