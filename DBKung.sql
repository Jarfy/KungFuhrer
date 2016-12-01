
CREATE TABLE IF NOT EXISTS production (
  idproduction int NOT NULL AUTO_INCREMENT,
  title VARCHAR(40) NOT NULL,
  year DATE,
  genre VARCHAR(200),
  released VARCHAR(100),
  runtime int,
  plott VARCHAR(200),
  languaje varchar(20),
  type VARCHAR(20),
  totalSeason int,
  episode int,
  PRIMARY KEY (idproduction) 
);


CREATE TABLE IF NOT EXISTS personsproduction (
  idperson int NOT NULL AUTO_INCREMENT,
  name varchar(100),
  lastname varchar(100),
  type varchar(100), 
  idproduction int NOT NULL, 
  PRIMARY KEY (idperson),
  FOREIGN KEY (idproduction) REFERENCES production(idproduction)
);


CREATE TABLE IF NOT EXISTS user (
  iduser int NOT NULL AUTO_INCREMENT,
  name varchar(100) NOT NULL,
  lastname varchar(100) NOT NULL,
  mail varchar(100) NOT NULL,  
  PRIMARY KEY (iduser)  
);

CREATE TABLE IF NOT EXISTS vote (
  idvoto int NOT NULL AUTO_INCREMENT,
  iduser int NOT NULL,
  idproduction int NOT NULL,
  votes int NOT NULL,  
  PRIMARY KEY (idvoto),
  FOREIGN KEY (iduser) REFERENCES user(iduser),
  FOREIGN KEY (idproduction) REFERENCES production(idproduction)
);





