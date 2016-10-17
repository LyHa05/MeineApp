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
	(KategorieID INTEGER GENERATED AS IDENTITY
	,Kategorie VARCHAR(100)
  ,PRIMARY KEY (KategorieID));

CREATE TABLE StammdatenWert
	(WertID INTEGER GENERATED AS IDENTITY
	,KategorieID INTEGER REFERENCES StammdatenKategorie(KategorieID)
	,Wert VARCHAR(100)
  ,PRIMARY KEY (WertID));

CREATE TABLE Person
	(PersonID INTEGER GENERATED AS IDENTITY
	,Name VARCHAR(50) 
	,Vorname1 VARCHAR(20)
	,Vorname2 VARCHAR(20)
	,Geschlecht VARCHAR(20)
	,Geburtsdatum DATE
	,HandyNr1 VARCHAR(30)
	,HandyNr2 VARCHAR(30)
  ,PRIMARY KEY (PersonID));
	
CREATE TABLE EMail
	(EMailID INTEGER GENERATED AS IDENTITY
	,EMailAdresse VARCHAR(100)
	,Gehoert INTEGER REFERENCES Person(PersonID)
  ,PRIMARY KEY (EMailID));

CREATE TABLE Adresse
	(AdressID INTEGER GENERATED AS IDENTITY
	,Strasse VARCHAR(50)
	,Zusatz VARCHAR(50)
	,PLZ VARCHAR(24)
	,Ort VARCHAR(50)
	,Land VARCHAR(100)
	,FestnetzNr Varchar(30)
  ,PRIMARY KEY (AdressID));

CREATE TABLE WohnhaftIn
	(PersonID INTEGER REFERENCES Person(PersonID)
	,AdressID INTEGER REFERENCES Adresse(AdressID)
	,PRIMARY KEY(PersonID, AdressID)
	,Hauptadresse INTEGER); -- Hauptadresse 1, Nebenadresse 0

CREATE TABLE Geschenk
	(GeschenkID INTEGER GENERATED AS IDENTITY
	,Jahr INTEGER
	,Anlass VARCHAR(100)
	,Memo VARCHAR(255)
	,Preis INTEGER
	,Erhaelt INTEGER REFERENCES Person(PersonID)
  ,PRIMARY KEY(GeschenkID));

CREATE TABLE GeschenkBestandteil
	(GeschenkBestandteilID INTEGER GENERATED AS IDENTITY
	,Beschreibung VARCHAR(100)
	,Memo VARCHAR(255)
	,Kategorie VARCHAR(100)
	,Bestandteil VARCHAR(100)
	,BestandteilVon INTEGER REFERENCES Geschenk(GeschenkID)
  ,PRIMARY KEY(GeschenkBestandteilID));

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
	JOIN EMail ON Person.PersonID = EMail.Gehoert;

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
	JOIN GeschenkBestandteil ON GeschenkBestandteil.BestandteilVon = Geschenk.GeschenkID;
