DROP TABLE IF EXISTS currency;

CREATE TABLE currency ( 
  id INT NOT NULL AUTO_INCREMENT, 
  name varchar(255),
  code varchar(255),
  rate_float float,
  description varchar(255) ,
  created_at datetime DEFAULT current_timestamp(),
  updated_at datetime DEFAULT current_timestamp() ON UPDATE current_timestamp()
);
