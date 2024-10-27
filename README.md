# veterinary-clinic

Diese Anwendung ist für eine Tierklinik konzipiert, um Tierakten, Impfungen, Erinnerungen und Termine zu verwalten. Sie hilft Tierärzten, Tierbesitzern und Klinikmitarbeitern, Impftermine im Auge zu behalten, Testergebnisse zu dokumentieren und Erinnerungen für bevorstehende Termine oder Impfungen zu setzen. Das Hauptziel ist es, sicherzustellen, dass Tiere rechtzeitig medizinisch versorgt werden und eine umfassende Krankenakte für jedes Tier geführt wird.



1.	User
	•	Attribute: id, name, email, type
	•	Repräsentiert die Benutzer des Systems, einschließlich Tierbesitzern und Tierärzten.
2.	Pet
	•	Attribute: id, species, breed, birth_date, vaccination_record
	•	Enthält Informationen zu jedem Tier im System der Klinik.
3.	VaccinationRecord
	•	Attribute: vacc_id, pet_id, vacc_name, administration_date, expiration_date
	•	Verfolgt die Impfdaten für jedes Tier und ist mit spezifischen Impfstofftypen verknüpft.
4.	VaccType
	•	Attribute: frequency, dispensability, availability
	•	Definiert die Arten von Impfstoffen, einschließlich ihrer empfohlenen Häufigkeit und Verfügbarkeit.
5.	Appointment
	•	Attribute: app_id, pet_id, vet_id, date, time, type_of_app
	•	Verwalten der Terminplanung für Tiere bei bestimmten Tierärzten.
6.	Notification
	•	Attribute: id, user_id, message, date_time, read_status
	•	Sendet Benachrichtigungen und Erinnerungen an Benutzer über bevorstehende Termine oder Impfungen.
7.	Reminder
	•	Attribute: vacc_remind(), test_rem()
	•	Bietet Methoden zum Auslösen von Erinnerungen basierend auf Impf- und Testplänen.
8.	Veterinarian
	•	Attribute: specialization
	•	Speichert Spezialisierungsinformationen für Tierärzte und verknüpft diese mit bestimmten Terminen.
9.	Test_Record
	•	Attribute: test_type, date, results
	•	Dokumentiert die Ergebnisse von medizinischen Tests, die bei Tieren durchgeführt wurden.
10.	Disease
	•	Attribute: name, type
	•	Erfasst häufige Krankheiten, um Tierärzten die Überwachung von Gesundheitsproblemen zu ermöglichen.


1:n-Beziehungen: Ein Veterinarian kann mehrere Appointments durchführen.
n:m-Beziehungen: Tiere und Krankheiten sind miteinander verbunden, da Tiere von mehreren Krankheiten betroffen sein können.
Komposition: VaccinationRecord enthält eine Referenz zu VaccType, was eine Kompositionsbeziehung darstellt, bei der jeder Impfdatensatz einem bestimmten Impfstofftyp zugeordnet ist.
Vererbung und Schnittstellen:
Eine abstrakte Klasse Reminder ist definiert mit Methoden wie vacc_remind() und test_rem(), um spezifische Erinnerungsfunktionen zu unterstützen. 
