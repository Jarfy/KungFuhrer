CREATE USER 'Kungfury'@'localhost' IDENTIFIED BY 'Kungpass';
GRANT ALL PRIVILEGES ON si . * TO 'Kungfury'@'localhost';
FLUSH PRIVILEGES;


create database IF NOT EXISTS si;
use  si;
CREATE TABLE IF NOT EXISTS production (
  idproduction int NOT NULL AUTO_INCREMENT,
  title VARCHAR(40) NOT NULL,
  year DATE,
  genre VARCHAR(200),
  released VARCHAR(100),
  runtime int,
  plot VARCHAR(200),
  languaje varchar(20),
  type VARCHAR(20),
  totalSeason int,
  episode int,
  PRIMARY KEY (idproduction) 
);

CREATE TABLE IF NOT EXISTS persons (
  idperson int NOT NULL AUTO_INCREMENT,
  name varchar(100),
  lastname varchar(100),
  PRIMARY KEY (idperson)
);

CREATE TABLE IF NOT EXISTS personsproduction (
  idperson int NOT NULL,
  idproduction int NOT NULL, 
  type varchar(100) NOT NULL,
  PRIMARY KEY (idperson,idproduction,type)
);


CREATE TABLE IF NOT EXISTS user (
  email varchar(100) NOT NULL, 
  name varchar(100) NOT NULL,
  lastname varchar(100) NOT NULL,   
  password varchar(16) NOT NULL,
  PRIMARY KEY (email)  
);


CREATE TABLE IF NOT EXISTS userAccess (
  email varchar(100) NOT NULL,  
  password varchar(16) NOT NULL,
  user boolean NOT NULL,
  employe boolean NOT NULL,
  admin boolean NOT NULL,   
  FOREIGN KEY (email) REFERENCES user(email)
);


CREATE TABLE IF NOT EXISTS vote (
  idvoto int NOT NULL AUTO_INCREMENT,
  idproduction int NOT NULL,
  rating int NOT NULL,  
  email varchar(100) NOT NULL,
  PRIMARY KEY (idvoto),
  FOREIGN KEY (email) REFERENCES user(email),
  FOREIGN KEY (idproduction) REFERENCES production(idproduction)
);