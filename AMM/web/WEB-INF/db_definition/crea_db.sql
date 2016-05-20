CREATE TABLE Utenti(
	username VARCHAR(32) PRIMARY KEY,
	password VARCHAR(32) NOT NULL,
	saldo DOUBLE,
	tipo CHAR(1) NOT NULL,
	feedback INTEGER
);

CREATE TABLE Articoli(
	id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
	titolo VARCHAR(64) NOT NULL,
	prezzo DOUBLE NOT NULL,
	numero INTEGER NOT NULL,
	image_path VARCHAR(64),
	descrizione VARCHAR(1024),
	venditore VARCHAR(32) NOT NULL,
	FOREIGN KEY(venditore) REFERENCES Utenti(username)
);


INSERT INTO Utenti(username,password,saldo,tipo,feedback)values
	('mario_rossi','0',50,'V',35),
	('francesco_neri','1',125.50,'V',70),
	('giovanni84','4',0,'V',90),
	('mario04','3',162,'C',null),
	('giovanni_verdi','2',125,'C',null);
	
	
INSERT INTO Articoli(id,titolo,prezzo,numero,image_path,descrizione,venditore)values
	(default,'Inside out',19.9,15,'images/inside_out.jpg','Animazione','mario_rossi'),
	(default,'Maze Runner',21,2,'images/maze_runner.jpg','Azione','mario_rossi'),
	(default,'Tales of Halloween',15.9,12,'images/hallowen.jpg','Horror','mario_rossi'),
	(default,'Crimson pick',19.9,1,'images/crimson_pick.jpg','Thriller','francesco_neri'),
	(default,'Snoopy and friends',15,12,'images/snoopy.jpg','Animazione','francesco_neri'),
	(default,'Snoopy and friends',17.5,5,'images/snoopy.jpg','Animazione','giovanni84'),
	(default,'Maze Runner',18.99,21,'images/maze_runner.jpg','Azione','giovanni84');
	