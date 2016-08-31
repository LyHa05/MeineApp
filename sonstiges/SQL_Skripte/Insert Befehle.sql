USE test4

INSERT INTO Person (PersonID, Name, Vorname1, Geschlecht, Geburtsdatum, HandyNr1, EMailAdresse1)
	VALUES (NEXT VALUE FOR PersonIDSequence, 'Meier', 'Karl', 'Männlich', '1988-10-30', '0123456', '123@web.de');
GO

INSERT INTO Person (PersonID, Name, Vorname1, Geschlecht, Geburtsdatum)
	VALUES (NEXT VALUE FOR PersonIDSequence, 'Meier', 'Karla', 'Weiblich', '1990-07-14');
GO

INSERT INTO Adresse
	VALUES (NEXT VALUE FOR AdressIDSequence, 'Unter der Brücke 14', NULL, '12345', 'Berlin', 'D', NULL)
GO

INSERT INTO Adresse
	VALUES (NEXT VALUE FOR AdressIDSequence, 'Auf der Bank 1', NULL, '12355', 'Berlin', 'D', NULL)
GO

INSERT INTO WohnhaftIn
	VALUES (1000, 2000)
GO

INSERT INTO WohnhaftIn
	VALUES(1000, 2001)
GO

INSERT INTO WohnhaftIn
	VALUES (1001, 2000)
GO

INSERT INTO G
	VALUES(NEXT VALUE FOR GIDSequence, 2016, 'G', NULL, 15, '1000')
GO

INSERT INTO GB
	VALUES(NEXT VALUE FOR GBIDSequence
			,'Bl'
			,NULL
			,'GEB'
			,'K'
			,(SELECT MAX(GID) FROM G))
GO

INSERT INTO GB
	VALUES(NEXT VALUE FOR GBIDSequence	
		,'Test-P2'
		,NULL
		,'GEK'
		,'P'
		,(SELECT MAX(GID) FROM G))
GO

INSERT INTO GB
	VALUES(NEXT VALUE FOR GBIDSequence	
		,'Test-P'
		,NULL
		,'GEK'
		,'P'
		,(SELECT MAX(GID) FROM G))
GO

INSERT INTO G
	VALUES(NEXT VALUE FOR GIDSequence, 2015, 'G', NULL, 20, '1000')
GO

INSERT INTO GB
	VALUES(NEXT VALUE FOR GBIDSequence
			,'Ba'
			,NULL
			,'GEK'
			,'K'
			,(SELECT MAX(GID) FROM G))
GO

INSERT INTO GB
	VALUES(NEXT VALUE FOR GBIDSequence	
		,'Test-P2'
		,NULL
		,'GEK'
		,'P'
		,(SELECT MAX(GID) FROM G))
GO