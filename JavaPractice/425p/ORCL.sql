CREATE TABLE Person(
	name VARCHAR(256),
	date_of_birth DATE,
	gender VARCHAR(6),
	position VARCHAR(12)
	);	
	
CREATE TABLE Movie(
	title VARCHAR(256) NOT NULL,
	PG_rating VARCHAR(5) NOT NULL,
	release_year INT NOT NULL,
	length NUMERIC(8) NOT NULL,
  actor VARCHAR(256),
  director VARCHAR(256),
  writer VARCHAR(256),
  PRIMARY KEY (title),
  FOREIGN KEY (actor) REFERENCES Actor (name),
  FOREIGN KEY (director) REFERENCES Director (name),
  FOREIGN KEY (writer) REFERENCES Writer (name)
	);

CREATE TABLE Showing(
	time DATE,
	room INT REFERENCES Room,
	title VARCHAR(256) REFERENCES Movie,
	PRIMARY KEY (title, time, room)
	);
	
CREATE TABLE Ticket(
  sn CHAR(6),
  time DATE,
  room INT,
  title VARCHAR(256),
	price NUMERIC(4),
	owner VARCHAR(256),
	PRIMARY KEY (sn, title, time, room, owner),
	FOREIGN KEY (time, room, title) REFERENCES Showing (time, room, title)
	);
	
CREATE TABLE Room(
	room_number INT PRIMARY KEY,
	capacity INT
	);
	
CREATE TABLE Users(
	ID INT PRIMARY KEY,
	name VARCHAR(256),
	phone VARCHAR(12),
	address VARCHAR(256),
	date_of_birth DATE
	);
	
CREATE TABLE Staff(
	staff_code INT,
  name VARCHAR(265)
	);
	
CREATE TABLE Actor(
	name VARCHAR(256) PRIMARY KEY,
	date_of_birth DATE,
	gender VARCHAR(6)
	);
CREATE TABLE Director(
	name VARCHAR(256) PRIMARY KEY,
	date_of_birth DATE,
	gender VARCHAR(6)
	);
CREATE TABLE Writer(
	name VARCHAR(256) PRIMARY KEY,
	date_of_birth DATE,
	gender VARCHAR(6)
	);