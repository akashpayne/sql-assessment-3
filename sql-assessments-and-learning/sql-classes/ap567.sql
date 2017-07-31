
/*
 * Akash Payne ap567
 * Database Systems
 * Assessment 3
 */ 

 -- Client Table
CREATE TABLE Client
	(
		clientNo CHAR(10) NOT NULL PRIMARY KEY, 
		forename varchar(30) NOT NULL, 
		surname varchar(30) NOT NULL, 
		gender CHAR(1) CHECK ( gender IN ('F', 'M') ),-- doesn't work: ENUM('M','F'), 
		address varchar(50) NOT NULL, 
		telNo CHAR(16) NOT NULL, 
		proLicenceNo CHAR(25) NOT NULL,
		UNIQUE (proLicenceNo)
	);

-- Instructor Table	
CREATE TABLE Instructor 
	(
		instructorID CHAR(10) NOT NULL PRIMARY KEY, 
		forename varchar(30) NOT NULL, 
		surname varchar(30) NOT NULL, 
		gender CHAR(1) CHECK ( gender IN ('F', 'M') ),--ENUM('M','F') default 'M', 
		address varchar(50) NOT NULL, 
		telNo CHAR(16) NOT NULL, 
		licenceNo CHAR(25) NOT NULL,
		carNo CHAR(10) NOT NULL,
		UNIQUE(licenceNo),
		FOREIGN KEY (carNO) REFERENCES Car (carNO)
			ON UPDATE CASCADE
			ON DELETE RESTRICT
	);

-- Car Table	
CREATE TABLE Car
	(
		carNo CHAR(10) NOT NULL PRIMARY KEY, 
		regNo CHAR(7) NOT NULL, 
		model varchar(30) NOT NULL,
		UNIQUE(regNo)
	);

-- Lesson Table	
CREATE TABLE Lesson
	(
		clientNo CHAR(10) NOT NULL, 
		onDate DATE DEFAULT NULL,
		onTime TIME DEFAULT NULL,
		instructorID CHAR(10) NOT NULL,
		PRIMARY KEY (instructorID, onDate, onTime),
		FOREIGN KEY (clientNo) REFERENCES Client (clientNo)
			ON UPDATE CASCADE
			ON DELETE RESTRICT,
		FOREIGN KEY (instructorID) REFERENCES Instructor (instructorID)
			ON UPDATE CASCADE
			ON DELETE RESTRICT,
		UNIQUE(instructorID, onDate, onTime)
	);

-- Test Table	
CREATE TABLE Test
	(
		clientNo CHAR(10) NOT NULL PRIMARY KEY, 
		onDate DATE DEFAULT NULL,
		onTime TIME DEFAULT NULL,
		instructorID CHAR(10) NOT NULL FOREIGN KEY,
		centreID CHAR(10) NOT NULL FOREIGN KEY,
		status CHAR(4) 
			CHECK (status IN ('Pass', 'Fail', NULL) ),-- ENUM('Fail','Pass') default 'NULL',
		reason varchar(200) 
		PRIMARY key (clientNo,onDate,atTime),
		FOREIGN KEY (clientNo) REFERENCES Client (clientNo)    
			ON UPDATE CASCADE
			ON DELETE RESTRICT, 
		FOREIGN KEY (instructorID) REFERENCES Instructor (instructorID)
			ON UPDATE CASCADE
			ON DELETE RESTRICT,
		FOREIGN KEY (centreID) REFERENCES Centre (centreID)
			ON UPDATE CASCADE
			ON DELETE RESTRICT,
		UNIQUE(instructorID,onDate,atTime) 
	);

-- Centre Table	
CREATE TABLE Centre
	(
		centreID CHAR(10) NOT NULL PRIMARY KEY, 
		name varchar(30), 
		address varchar(30)  
	);

-- Client Data 	
INSERT INTO Client 
	(
		clientNo, 
		forename,
		surname,
		gender,
		address,
		telNo,
		proLicenceNo
	)
	VALUES
	(
		'1',
		'Jim',
		'Jones',
		'M',
		'1 The Sidings, Canterbury',
		'04695654978',
		'JONES7345919TSAD'
	),
	(
		'2',
		'Julie',
		'Anderson',
		'F',
		'34 High Street, Maidstone',
		'0116 629136',
		'ANDER173079QTAD'
	),
	(
		'3',
		'Jim',
		'Anderson',
		'M',
		'34 High Street, Maidstone',
		'07557 812015',
		'ANDER915277RBAD'
	);

-- Instructor Data 	
INSERT INTO instructorID
	(
		instructorID,
		forename,
		surname,
		gender,
		address,
		telNo,
		licenceNo,
		carNo
	)
	VALUES
	(
		'I2',
		'Jim',
		'Adderson',
		'M',
		'6 The Sidings, Canterbury',
		'08877173458',
		'ADDDER7123497RDFV',
		'7'
	),
	(
		'I1',
		'Jill',
		'Reid',
		'F',
		'Beach Cottage, Sandwich',
		'06518754368',
		'REID9157323ERTG',
		'9'
	),
	(
		'I3',
		'Sarah',
		'Jones',	
		'F',	
		'123 Stone Street, Pelham',	
		'004477546643334',	
		'JONES02313248111344FG',
		'1'
	);

-- Car Data 	
INSERT INTO Car
	(
		carNo,
		regNo,
		model
	)
	VALUES
	(
		'1',
		'TG13 IUR',
		'Ford Fiesta'

	),
	(
		'3',
		'RF10 AAF',
		'Ford Fiesta'
	),
	(
		'7',
		'QW64 YTR',
		'Ford Fiesta'
	),
	(
		'9',	
		'MA63 QWA',
		'Mini One'
	);

-- Lesson Data 	
INSERT INTO Lesson
	(
		clientNo, 
		onDate,
		onTime,
		instructorID
	)
	VALUES
	(
		'1',
		'01/03/2015',
		'14:00',
		'I3'
	),
	(
		'2',
		'01/03/2015',
		'14:00',
		'I1'
	),
	(
		'1',
		'08/03/2015',
		'10:00',
		'I2'
	),
	(
		'3',
		'08/03/2015',
		'10:00',
		'I1'
	),
	(
		'1',
		'15/03/2015',
		'14:00',
		'I3'
	);

-- Test Data 	
INSERT INTO Test
	(
		clientNo,
		onDate,
		onTime,
		instructorID,
		centreID,
		status,
		reason
	)
	VALUES
	(
		'2',
		'25/03/2015',
		'10:00',
		'I1',
		'1',
		'Fail',
		'Emergency Stop'
	)
	(
		'3',	
		'10/03/2015',
		'12:00',
		'I1',
		'1',
		'Pass',
		'NULL'
	)
	(
		'2',
		'29/04/2015',
		'10:00',
		'I2',
		'1',
		'NULL',
		'NULL'
	);

-- Queries 

-- a) How many people with the first name "Jim" have passed their test? 	
SELECT 
	COUNT(status) 
		AS NumberOfJimPassed 
FROM 
	Test
INNER JOIN 
	Client 
		ON Test.clientNo = Client.clientNo
WHERE 
	(Test.status = 'Pass') AND (Client.forename = 'Jim');
	
-- b) get the instructor forename, surname, the client forename, surname and date for every test after 9am on the 10/03/2015
SELECT 
	Instructor.forname, 
	Instructor.surname, 
	Client.forename, 
	Client.surname
FROM
	Test
INNER JOIN 
	Client 
		ON Test.ClientNo = Client.ClientNo
INNER JOIN 
	Instructor 
		ON Test.instructorID = Instructor.instructorID
WHERE
	(Test.onDate >= '10/03/2015') AND (Test.onTime = '09:00');
	
-- c) find the name and address for all clients with the date and time of their tests for everyone who has had a lesson with jill reid. 	
SELECT 
	Client.forename+' '+Client.surname 
		AS Name,
	Client.address, 
	Instructor.onDate, 
	Instructor.onTime	
FROM 
	Lesson
INNER JOIN 
	Client ON Lesson.ClientNo = Client.ClientNo
INNER JOIN
	Test ON Lesson.ClientNo = Test.ClientNo
INNER JOIN 
	Instructor 
		ON Lesson.instructorID = Instructor.instructorID
WHERE
	(Instructor.forename = 'Jim') AND (Instructor.surname = 'Ried');

-- 2 c) i) Alternative: 
SELECT 
	Test.clientNo, 
	Client.forename  ||'  '||Client.surname AS ClientName, 
	Client.address,
	Test.onDate, 
	Test.onTime,
	Instructor.forename FROM Test 
INNER JOIN 
	Client ON Test.clientNo = Client.clientNo 
INNER JOIN 
	Lesson ON Client.clientNo = Lesson.clientNo  
INNER JOIN 
	Instructor ON Test.instructorID = Instructor.instructorID 
WHERE (Instructor.forename ='Jill') AND (Instructor.surname='Reid');

	
-- d) Get the Forenames, Surnames and Telephone Numbers for all people, both clients and instructors in the database. 
SELECT 
	Client.forename, 
	Client.surname, 
	Client.telNo 
FROM 
	Client 
UNION
SELECT 
	Instructor.forename, 
	Instructor.surname, 
	Instructor.teleNo 
FROM 
	Instructor;

-- e) Instructor Jim Adderson switches to the Car with registration RF10 AAF. Give the SQL command to make the change
UPDATE 
	Instructor
SET 
	Instructor.RegNo = Car.RegNo
FROM 
	Instructor
INNER JOIN 
	Car 
		ON Car.RegNo = 'RF10 AFF'
WHERE 
	(Instructor.forname = 'Jim') AND (Instructor.surname = 'Adderson');
	
-- shorter: 
UPDATE 
	Instructor  
SET carno = ( 
	SELECT 
		Car.carNo 
	FROM 
		Car 
	WHERE 
		(Car.regNo = 'RF10 AAF') ) 
WHERE 
	(Instructor.forename='Jim') AND (Instructor.surname = 'Adderson');

-- f) The instructor for the test at 29/04/2015 10:00 has changed to be the instructor with a Mini One. Give the SQL command to make this change.
UPDATE 
	Test 
SET 
	instructorID = (
		Select 
			Instructor.instructorID 
		FROM 
			Instructor 
		WHERE carNo = (
			Select 
				Car.carNo 
			FROM 
				Car 
			WHERE 
				Car.model='Mini One'
		)
	) 
WHERE 
	(onDate='29-04-2015') AND (onTime='10:00');