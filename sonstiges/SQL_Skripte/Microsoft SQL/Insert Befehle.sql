USE test6

INSERT INTO Person (Name, Vorname1, Geschlecht, Geburtsdatum, HandyNr1)
	VALUES ('Meier', 'Karl', 'Männlich', '1988-10-30', '0123456');
GO

Insert INTO EMail (EMailAdresse, Gehoert)
	VALUES ('123@web.de',1);
GO

INSERT INTO Person (Name, Vorname1, Geschlecht, Geburtsdatum)
	VALUES ('Meier', 'Karla', 'Weiblich', '1990-07-14');
GO

INSERT INTO Adresse (Strasse, Zusatz, PLZ, Ort, Land, FestnetzNr)
	VALUES ('Unter der Brücke 14', NULL, '12345', 'Berlin', 'D', NULL)
GO

INSERT INTO Adresse (Strasse, Zusatz, PLZ, Ort, Land, FestnetzNr)
	VALUES ('Auf der Bank 1', NULL, '12355', 'Berlin', 'D', NULL)
GO

INSERT INTO WohnhaftIn
	VALUES (1, 1, 1)
GO

INSERT INTO WohnhaftIn
	VALUES(1, 2, 0)
GO

INSERT INTO WohnhaftIn
	VALUES (2, 1, 1)
GO

INSERT INTO Geschenk (Jahr, Anlass, Memo, Preis, Erhaelt)
	VALUES(2016, 'G', NULL, 15, 1)
GO

INSERT INTO GeschenkBestandteil
	VALUES('Bl'
			,NULL
			,'GEB'
			,'K'
			,(SELECT MAX(GeschenkID) FROM Geschenk))
GO

INSERT INTO GeschenkBestandteil
	VALUES('Test-P2'
		,NULL
		,'GEK'
		,'P'
		,(SELECT MAX(GeschenkID) FROM Geschenk))
GO

INSERT INTO GeschenkBestandteil
	VALUES('Test-P'
		,NULL
		,'GEK'
		,'P'
		,(SELECT MAX(GeschenkID) FROM Geschenk))
GO

INSERT INTO Geschenk
	VALUES(2015, 'G', NULL, 20, 1)
GO

INSERT INTO GeschenkBestandteil
	VALUES('Ba'
			,NULL
			,'GEK'
			,'K'
			,(SELECT MAX(GeschenkID) FROM Geschenk))
GO

INSERT INTO GeschenkBestandteil
	VALUES('Test-P2'
		,NULL
		,'GEK'
		,'P'
		,(SELECT MAX(GeschenkID) FROM Geschenk))
GO