<?php
//These are the defined authentication environment in the db service

// The MySQL service named in the docker-compose.yml.
$host = 'db';

// Database use name
$user = 'MYSQL_USER';

//database user password
$pass = 'MYSQL_PASSWORD';

// database name
$mydatabase = 'MYSQL_DATABASE';
// check the mysql connection status

$conn = new mysqli($host, $user, $pass, $mydatabase);

// select query
$sql = 'SELECT * FROM users';

if ($result = $conn->query($sql)) {
    while ($data = $result->fetch_object()) {
        $users[] = $data;
    }
}

foreach ($users as $user) {
    echo "<br>";
    echo $user->username . " " . $user->password;
    echo "<br>";
}

// GET LAST ID
$sql = "INSERT INTO users (id, username, password) VALUES (7, 'Maria', '654321789')";

if ($conn->query($sql) === TRUE) {
  $last_id = $conn->insert_id;
  echo "<br>";
  echo "New record created successfully. Last inserted ID is: " . $last_id;
  echo "<br>";
} else {
  echo "<br>";
  echo "Error: " . $sql . "<br>" . $conn->error;
}

// SELECT WHERE NAME IS MARIA

$sql = "SELECT id, username, password FROM users WHERE username='Alice'";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
  // output data of each row
  while($row = $result->fetch_assoc()) {
    echo "<br>";
    echo "<br>";
    echo "id: " . $row["id"]. " - NAME: " . $row["username"]. " - PASSWORD: " . $row["password"]. "<br>";
  }
} else {
  echo "<br>";
  echo "0 results";
  echo "<br>";
}

// UPDATE VALUE

$sql = "UPDATE users SET username='Marcos' WHERE id=7";

if ($conn->query($sql) === TRUE) {
  echo "<br>";
  echo "Record updated successfully";
  echo "<br>";
} else {
  echo "<br>";
  echo "Error updating record: " . $conn->error;
  echo "<br>";
}

$conn->close();

?>
