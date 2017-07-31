


CREATE TABLE Client
(
	clientNo CHAR(10) NOT NULL PRIMARY KEY, 
	forename varchar(30) NOT NULL, 
	surname varchar(30) NOT NULL, 
	gender ENUM('M','F') default 'M', 
	address varchar(50) NOT NULL, 
	telNo CHAR(11) NOT NULL, 
	proLicenceNo CHAR(10) NOT NULL
)

CREATE TABLE Instructor 
(
	instructorID CHAR(10) NOT NULL PRIMARY KEY, 
	forename varchar(30) NOT NULL, 
	surname varchar(30) NOT NULL, 
	gender ENUM('M','F') default 'M', 
	address varchar(50) NOT NULL, 
	telNo CHAR(11) NOT NULL, 
	licenceNo CHAR(10) NOT NULL,
	carNo CHAR(10) NOT NULL FOREIGN KEY
)

CREATE TABLE Car
(
	carNo CHAR(10) NOT NULL PRIMARY KEY, 
	regNo CHAR(7) NOT NULL, 
	model varchar(30) NOT NULL, 
)

CREATE TABLE Lesson
(
	clientNo CHAR(10) NOT NULL PRIMARY KEY, 
	onDate DATE DEFAULT NULL,
	onTime TIME DEFAULT NULL,
	instructorID CHAR(10) NOT NULL FOREIGN KEY
)

CREATE TABLE Test
(
	clientNo CHAR(10) NOT NULL PRIMARY KEY, 
	onDate DATE DEFAULT NULL,
	onTime TIME DEFAULT NULL,
	instructorID CHAR(10) NOT NULL FOREIGN KEY,
	centreID CHAR(10) NOT NULL FOREIGN KEY,
	status ENUM('Fail','Pass') default 'F',
	reason varchar(30) NOT NULL
)
	
CREATE TABLE Centre
(
	clientID CHAR(10) NOT NULL PRIMARY KEY, 
	name varchar(30) NOT NULL, 
	address varchar(30) NOT NULL 
)
