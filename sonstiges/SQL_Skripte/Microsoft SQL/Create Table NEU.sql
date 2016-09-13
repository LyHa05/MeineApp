USE test6

DROP TABLE WohnhaftIn;
DROP TABLE Adresse;
DROP TABLE GeschenkBestandteil;
DROP TABLE Geschenk;
DROP TABLE EMail;
DROP TABLE Person;
DROP TABLE StammdatenWert;
DROP TABLE StammdatenKategorie;
DROP VIEW PersonUebersicht;
DROP VIEW GeschenkUebersicht;

CREATE TABLE StammdatenKategorie
	(KategorieID INTEGER PRIMARY KEY IDENTITY(1,1) NOT NULL
	,Kategorie VARCHAR(100))

CREATE TABLE StammdatenWert
	(WertID INTEGER PRIMARY KEY IDENTITY(1,1) NOT NULL
	,KategorieID INTEGER REFERENCES StammdatenKategorie(KategorieID)
	,Wert VARCHAR(100))

CREATE TABLE Person
	(PersonID INTEGER PRIMARY KEY IDENTITY(1,1) NOT NULL
	,Name VARCHAR(50) 
	,Vorname1 VARCHAR(20)
	,Vorname2 VARCHAR(20)
	,Geschlecht INTEGER REFERENCES StammdatenWert(WertID)
	,Geburtsdatum DATE
	,HandyNr1 VARCHAR(30)
	,HandyNr2 VARCHAR(30));
	
CREATE TABLE EMail
	(EMailID INTEGER PRIMARY KEY IDENTITY(1,1) NOT NULL
	,EMailAdresse VARCHAR(100)
	,Gehoert INTEGER REFERENCES Person(PersonID));

CREATE TABLE Adresse
	(AdressID INTEGER PRIMARY KEY IDENTITY(1,1) NOT NULL
	,Strasse VARCHAR(50)
	,Zusatz VARCHAR(50)
	,PLZ VARCHAR(24)
	,Ort VARCHAR(50)
	,Land INTEGER REFERENCES StammdatenWert(WertID)
	,FestnetzNr Varchar(30));

CREATE TABLE WohnhaftIn
	(PersonID INTEGER REFERENCES Person(PersonID)
	,AdressID INTEGER REFERENCES Adresse(AdressID)
	,PRIMARY KEY(PersonID, AdressID)
	,Hauptadresse INTEGER); -- Hauptadresse 1, Nebenadresse 0

CREATE TABLE Geschenk
	(GeschenkID INTEGER PRIMARY KEY IDENTITY(1,1) NOT NULL
	,Jahr INTEGER
	,Anlass INTEGER REFERENCES StammdatenWert(WertID)
	,Memo VARCHAR
	,Preis INTEGER
	,Erhaelt INTEGER REFERENCES Person(PersonID));

CREATE TABLE GeschenkBestandteil
	(GeschenkBestandteilID INTEGER PRIMARY KEY IDENTITY(1,1) NOT NULL
	,Beschreibung VARCHAR(100)
	,Memo VARCHAR(255)
	,Kategorie INTEGER REFERENCES StammdatenWert(WertID)
	,Bestandteil INTEGER REFERENCES StammdatenWert(WertID)
	,BestandteilVon INTEGER REFERENCES Geschenk(GeschenkID))


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
		,EMail.EMailAdresse
	FROM Person JOIN WohnhaftIn ON Person.PersonID = WohnhaftIn.PersonID
	JOIN Adresse ON WohnhaftIn.AdressID = Adresse.AdressID
	JOIN EMail ON Person.PersonID = EMail.Gehoert
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

