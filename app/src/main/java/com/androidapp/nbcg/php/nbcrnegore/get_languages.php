<?php 
	$host = "localhost";
	$user = "root";
	$pass = "";
	$db = "localdbnbcg";
	
	$con = mysqli_connect($host, $user, $pass, $db);
	
	$query = "SELECT * FROM languages WHERE active=1";
	
	$result = mysqli_query($con, $query);
	
	$response = array();
	
	while($row = mysqli_fetch_array($result)){
		array_push($response, array("SEND_LANGCODE"=>$row['lang_code'],
									"SEND_LANG"=>$row['lang'],
									"SEND_LANGSHORT"=>$row['lang_short'],
									"SEND_LANGTHREE"=>$row['lang_three'],
									"SEND_ACTIVE"=>$row['active'])
									);
	}

	mysqli_close($con);
	
	echo json_encode(array('server_response'=>$response));