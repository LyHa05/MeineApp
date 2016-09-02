USE test4
GO

DROP PROCEDURE PersonErstellen;
GO

CREATE PROCEDURE PersonErstellen 
	(@Name VARCHAR(50)
	,@Vorname1 VARCHAR(20)
	,@Vorname2 VARCHAR(20)
	,@Geschlecht VARCHAR(20)
	,@HandyNr1 VARCHAR(30)
	,@HandyNr2 VARCHAR(30)
	,@EMail1 VARCHAR(50)
	,@EMail2 VARCHAR(50)
	,@EMail3 VARCHAR(50)
	,@EMail4 VARCHAR(50)
	,@EMail5 VARCHAR(50)
	,@Strasse VARCHAR(50)
	,@Zusatz VARCHAR(50)
	,@PLZ VARCHAR(24)
	,@Ort VARCHAR(50)
	,@Land VARCHAR(3)
	,@FestnetzNr VARCHAR(30))
AS
	DECLARE @PersonID INT
	SET @PersonID = NEXT VALUE FOR PersonIDSequence
	DECLARE @AdressID INT
	SET @AdressID = NEXT VALUE FOR AdressIDSequence
BEGIN TRANSACTION InsertPerson
	INSERT INTO Person
		VALUES(@PersonID, @Name, @Vorname1, @Vorname2, @Geschlecht, @HandyNr1, @HandyNr2, @EMail1, @EMail2, @EMail3, @EMail4, @EMail5);
	INSERT INTO Adresse
		VALUES(@AdressID, @Strasse, @Zusatz, @PLZ, @Ort, @Land, @FestnetzNr);
	INSERT INTO WohnhaftIn
		VALUES(@PersonID, @AdressID);
COMMIT TRANSACTION InsertPerson
GO

DROP PROCEDURE GErstellen
GO

CREATE PROCEDURE GErstellen
	(@GIDVerweis INT
	,@Jahr INT
	,@Anlass VARCHAR(50)
	,@GMemo VARCHAR(255)
	,@Erhaelt INT
	,@Motiv VARCHAR(100)
	,@KMemo VARCHAR(255)
	,@KKategorie VARCHAR(50)
	,@Preis INT
	,@PMemo VARCHAR(255)
	,@PKategorie VARCHAR(50))
	AS
	DECLARE @GIDBerechnet INT
	SET @GIDBerechnet = NEXT VALUE FOR GIDSequence

	--vollständige Neuanlage von G--
	IF @GIDVerweis IS NULL
		BEGIN 
			INSERT INTO G
				VALUES(@GIDBerechnet, @Jahr, @Anlass, @GMemo, @Erhaelt);
			INSERT INTO K
				VALUES(@GIDBerechnet, @Motiv, @KMemo, @KKategorie);
			INSERT INTO P
				VALUES(@GIDBerechnet, @Preis, @PMemo, @PKategorie);
		END
	ELSE IF @GIDVerweis IS NOT NULL
	--Ergänzung von K bei bereits vorhandenem G--
		BEGIN
			INSERT INTO K
				VALUES(@GIDVerweis, @Motiv, @KMemo, @KKategorie);
		END
	END
	

	--Ergänzung von P bei bereits vorhandenem G--

GO

-- Vorbereitung von Prozeduren für alle 3 Fälle: Insert, Update und Delete
-- Kennzeichen für jeweiligen Fall mitführen, sodass mit IF-Klausel unterschieden werden kann

