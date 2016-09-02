	USE test4
	
	SELECT 
		Person.PersonID
		,Person.Name
		,Person.Vorname1
		,Person.Vorname2
		,Person.Geschlecht
		,Person.Geburtsdatum
		,Person.HandyNr1
		,Person.HandyNr2
		,Person.EMailAdresse1
		,Person.EMailAdresse2
		,Person.EMailAdresse3
		,Person.EMailAdresse4
		,Person.EMailAdresse5
		,Adresse.AdressID
		,Adresse.Strasse
		,Adresse.Zusatz
		,Adresse.PLZ
		,Adresse.Ort
		,Adresse.Land
		,Adresse.FestnetzNr
	FROM Person JOIN WohnhaftIn ON Person.PersonID = WohnhaftIn.PersonID
	JOIN Adresse ON WohnhaftIn.AdressID = Adresse.AdressID
