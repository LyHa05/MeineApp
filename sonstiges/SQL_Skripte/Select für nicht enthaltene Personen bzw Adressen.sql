Use test4

	SELECT DISTINCT WohnhaftIn.PersonID
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
			FROM WohnhaftIn JOIN Person ON WohnhaftIn.PersonID = Person.PersonID
			WHERE WohnhaftIn.PersonID Not In (Select Person.PersonID FROM Person JOIN WohnhaftIn ON WohnhaftIn.PersonID = Person.PersonID
																	JOIN Adresse ON Adresse.AdressID = WohnhaftIn.AdressID
																	WHERE Adresse.AdressID = 2004)
	
	SELECT DISTINCT WohnhaftIn.AdressID
			,Adresse.Strasse
			,Adresse.Zusatz
			,Adresse.PLZ
			,Adresse.Ort
			,Adresse.Land
			,Adresse.FestnetzNr
			FROM Adresse JOIN WohnhaftIn ON Adresse.AdressID = WohnhaftIn.AdressID
			WHERE WohnhaftIn.AdressID Not In (Select Adresse.AdressID FROM Adresse JOIN WohnhaftIn ON WohnhaftIn.AdressID = Adresse.AdressID
																	JOIN Person ON Person.PersonID = WohnhaftIn.PersonID
																	WHERE Person.PersonID = 1000)