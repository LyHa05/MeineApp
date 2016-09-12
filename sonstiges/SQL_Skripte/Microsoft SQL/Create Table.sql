USE test5

DROP TABLE WohnhaftIn;
DROP TABLE Adresse;
DROP TABLE GeschenkBestandteil;
DROP TABLE Geschenk;
DROP TABLE Person;
DROP TABLE StammdatenWerte;
DROP TABLE StammdatenKategorie;
DROP VIEW PersonUebersicht;
DROP VIEW GeschenkUebersicht;

CREATE TABLE Person
	(PersonID INTEGER PRIMARY KEY IDENTITY(1,1) NOT NULL
	,Name VARCHAR(50) 
	,Vorname1 VARCHAR(20)
	,Vorname2 VARCHAR(20)
	,Geschlecht VARCHAR(20)
	,Geburtsdatum DATE
	,HandyNr1 VARCHAR(30)
	,HandyNr2 VARCHAR(30)
	,EMailAdresse1 VARCHAR(50)
	,EMailAdresse2 VARCHAR(50)
	,EMailAdresse3 VARCHAR(50)
	,EMailAdresse4 VARCHAR(50)
	,EMailAdresse5 VARCHAR(50));

CREATE TABLE Adresse
	(AdressID INTEGER PRIMARY KEY IDENTITY(1,1) NOT NULL
	,Strasse VARCHAR(50)
	,Zusatz VARCHAR(50)
	,PLZ VARCHAR(24)
	,Ort VARCHAR(50)
	,Land VARCHAR(50)
	,FestnetzNr Varchar(30));


CREATE TABLE WohnhaftIn
	(PersonID INTEGER REFERENCES Person(PersonID)
	,AdressID INTEGER REFERENCES Adresse(AdressID)
	,PRIMARY KEY(PersonID, AdressID)
	,Hauptadresse INTEGER); -- Hauptadresse 1, Nebenadresse 0

CREATE TABLE Geschenk
	(GeschenkID INTEGER PRIMARY KEY IDENTITY(1,1) NOT NULL
	,Jahr INTEGER
	,Anlass VARCHAR(50)
	,Memo VARCHAR
	,Preis INTEGER
	,Erhaelt INTEGER REFERENCES Person(PersonID));

CREATE TABLE GeschenkBestandteil
	(GeschenkBestandteilID INTEGER PRIMARY KEY IDENTITY(1,1) NOT NULL
	,Beschreibung VARCHAR(100)
	,Memo VARCHAR(255)
	,Kategorie VARCHAR(50)
	,Bestandteil VARCHAR(50)
	,BestandteilVon INTEGER REFERENCES Geschenk(GeschenkID))

CREATE TABLE StammdatenKategorie
	(KategorieID INTEGER PRIMARY KEY IDENTITY(1,1) NOT NULL
	,Kategorie VARCHAR(100))

CREATE TABLE StammdatenWerte
	(WertID INTEGER PRIMARY KEY IDENTITY(1,1) NOT NULL
	,KategorieID INTEGER REFERENCES StammdatenKategorie(KategorieID)
	,Wert VARCHAR(100))
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

CREATE VIEW GeschenkUebersicht AS
	SELECT Person.Name
		,Person.Vorname1 AS Vorname
		,Geschenk.Jahr
		,Geschenk.Anlass
		,GeschenkBestandteil.Beschreibung
		,Geschenk.Preis
		,GeschenkBestandteil.Kategorie
		,GeschenkBestandteil.Bestandteil
	FROM Person JOIN Geschenk ON Person.PersonID = Geschenk.Erhaelt
	JOIN GeschenkBestandteil ON GeschenkBestandteil.BestandteilVon = Geschenk.GeschenkID
GO

