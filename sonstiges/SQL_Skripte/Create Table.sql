USE test4

DROP TABLE WohnhaftIn;
DROP TABLE Adresse;
DROP TABLE GB;
DROP TABLE G;
DROP TABLE Person;
DROP SEQUENCE PersonIDSequence;
DROP SEQUENCE AdressIDSequence;
DROP SEQUENCE GIDSequence;
DROP SEQUENCE GBIDSequence;
DROP VIEW PersonUebersicht;
DROP VIEW GUebersicht;

CREATE TABLE Person
	(PersonID INTEGER PRIMARY KEY
	,Name VARCHAR(50) NOT NULL
	,Vorname1 VARCHAR(20) NOT NULL
	,Vorname2 VARCHAR(20)
	,Geschlecht VARCHAR(20)
	,Geburtsdatum DATE
	,HandyNr1 VARCHAR(30)
	,HandyNr2 VARCHAR(30)
	,EMailAdresse1 VARCHAR(50)
	,EMailAdresse2 VARCHAR(50)
	,EMailAdresse3 VARCHAR(50)
	,EMailAdresse4 VARCHAR(50)
	,EMailAdresse5 VARCHAR(50)
	,CHECK(Geschlecht LIKE 'Männlich' OR Geschlecht LIKE 'Weiblich'));

CREATE TABLE Adresse
	(AdressID INTEGER PRIMARY KEY
	,Strasse VARCHAR(50)
	,Zusatz VARCHAR(50)
	,PLZ VARCHAR(24)
	,Ort VARCHAR(50)
	,Land VARCHAR(50)
	,FestnetzNr Varchar(30)
	,Druck INTEGER(1)
	,Druckanrede VARCHAR(50));


CREATE TABLE WohnhaftIn
	(PersonID INTEGER REFERENCES Person(PersonID)
	,AdressID INTEGER REFERENCES Adresse(AdressID)
	,PRIMARY KEY(PersonID, AdressID));

CREATE TABLE G
	(GID INTEGER PRIMARY KEY
	,Jahr INTEGER
	,Anlass VARCHAR(50)
	,Memo VARCHAR
	,Preis INTEGER
	,Erhaelt INTEGER REFERENCES Person(PersonID)
	,CHECK (Anlass LIKE 'G' OR Anlass LIKE 'H' OR Anlass LIKE 'J' OR Anlass LIKE 'Sonstiges'));

CREATE TABLE GB
	(GBID INTEGER PRIMARY KEY
	,Beschreibung VARCHAR(100)
	,Memo VARCHAR(255)
	,Kategorie VARCHAR(50)
	,Bestandteil VARCHAR(50)
	,BestandteilVon INTEGER REFERENCES G(GID)
	,CHECK (Kategorie LIKE 'Gek' OR Kategorie LIKE 'Geb' OR Kategorie LIKE 'Sonstiges')
	,CHECK (Bestandteil LIKE 'P' OR Bestandteil LIKE 'K'));

CREATE SEQUENCE PersonIDSequence
	START WITH 1000
	INCREMENT BY 1;

CREATE SEQUENCE AdressIDSequence
	START WITH 2000
	INCREMENT BY 1;

CREATE SEQUENCE GIDSequence
	START With 3000
	INCREMENT BY 1;

CREATE SEQUENCE GBIDSequence
	START With 4000
	INCREMENT BY 1;
GO

CREATE VIEW PersonUebersicht AS
	SELECT Person.Name
		,Person.Vorname1 AS Vorname
		,Adresse.Strasse
		,Adresse.Zusatz
		,Adresse.PLZ
		,Adresse.Ort
		,Person.HandyNr1 AS HandyNr
		,Adresse.FestnetzNr
		,Person.EMailAdresse1
	FROM Person JOIN WohnhaftIn ON Person.PersonID = WohnhaftIn.PersonID
	JOIN Adresse ON WohnhaftIn.AdressID = Adresse.AdressID
GO

CREATE VIEW GUebersicht AS
	SELECT Person.Name
		,Person.Vorname1 AS Vorname
		,G.Jahr
		,G.Anlass
		,GB.Beschreibung
		,G.Preis
		,GB.Kategorie
		,GB.Bestandteil
	FROM Person JOIN G ON Person.PersonID = G.Erhaelt
	JOIN GB ON GB.BestandteilVon = G.GID
GO

