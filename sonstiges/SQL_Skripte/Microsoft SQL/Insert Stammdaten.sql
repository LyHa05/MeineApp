USE test6

INSERT INTO StammdatenKategorie (Kategorie)
	VALUES('Geschlecht'), ('Land'), ('Anlass'), ('GeschenkKategorie'), ('Geschenkbestandteil');
	
INSERT INTO StammdatenWert (KategorieID, Wert)
	VALUES(1,'Männlich'), (1,'Weiblich');
	
INSERT INTO StammdatenWert (KategorieID, Wert)
	VALUES(2,'Deutschland'), (2,'Österreich'), (2,'Schweiz');
	
INSERT INTO StammdatenWert (KategorieID, Wert)
	VALUES(3,'Hochzeit'), (3, 'Geburtstag'), (3, 'Dankeschön'), (3, 'Weihnachten'), (3, 'Ostern');

INSERT INTO StammdatenWert (KategorieID, Wert)
	VALUES(4,'Gebastelt'), (4,'Gekauft');
	
INSERT INTO StammdatenWert (KategorieID, Wert)
	VALUES(5,'Karte'), (5,'Präsent');
	



	
