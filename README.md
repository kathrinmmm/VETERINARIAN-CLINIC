Anwendung zur veterinarian clinic

Die Verwaltung der Tiergesundheit kann für Tierbesitzer und Tierärzte oft komplex und zeitaufwendig sein. Diese Anwendung schafft Abhilfe, indem sie alle wichtigen Funktionen in einer benutzerfreundlichen Oberfläche zusammenfasst. Egal, ob es um die Terminplanung, die Verfolgung von Impfungen oder die Einsicht in medizinische Aufzeichnungen geht
Diese Anwendung wurde entwickelt, um Tierbesitzern und Tierärzten eine benutzerfreundliche Plattform bereitzustellen, die ihnen hilft, die Gesundheit von Haustieren effizient zu verwalten. Sie bietet verschiedene Funktionen, die sowohl die Organisation von Terminen als auch die Erfassung und Einsicht in medizinische Informationen erleichtern. Ziel der Anwendung ist es, den Alltag von Tierbesitzern und Tierärzten zu verbessern, indem sie alle wichtigen Informationen an einem Ort vereint.

Funktionen im Detail:
1. Verwaltung von Haustieren (createPet und deletePet)
- createPet: Erstellt ein neues Haustier und fügt es der Datenbank hinzu.
- deletePet: Löscht ein Haustier aus der Datenbank anhand seiner ID.
2. Verwaltung von Krankheiten (createDisease und addDiseaseToPet)
- createDisease: Erstellt eine neue Krankheit und fügt sie der Datenbank hinzu.
- addDiseaseToPet: Verknüpft eine Krankheit mit der Gesundheitsakte eines bestimmten Haustieres.
3. Verwaltung von Tierärzten (createVet, deleteVet, und sortVeterinariansBySpecialization)
- createVet: Erstellt einen neuen Tierarzt und fügt ihn der Datenbank hinzu.
- deleteVet: Löscht einen Tierarzt aus der Datenbank anhand seiner ID.
- sortVeterinariansBySpecialization: Sortiert Tierärzte nach ihrem Fachgebiet (Spezialisierung).
4. Verwaltung von Terminen (createAppointment, deleteApp, getAppointmentsByDate, getAppointmentsByPet, getAppointmentsByVet, getAppointmentsInDateRange, sortAppointmentsByDate)
- createAppointment: Erstellt einen neuen Termin und fügt ihn der Datenbank hinzu.
- deleteApp: Löscht einen Termin aus der Datenbank anhand seiner ID.
- getAppointmentsByDate: Ruft alle Termine für ein bestimmtes Datum ab.
- getAppointmentsByPet: Ruft alle Termine für ein bestimmtes Haustier ab.
- getAppointmentsByVet: Ruft alle Termine für einen bestimmten Tierarzt ab.
- getAppointmentsInDateRange: Ruft alle Termine innerhalb eines bestimmten Zeitraums ab.
- sortAppointmentsByDate: Sortiert Termine nach Datum.
5. Verwaltung von Gesundheitsdaten (getHealthRecordByPetId und createTest, createVaccine)
- getHealthRecordByPetId: Ruft die Gesundheitsakte eines bestimmten Haustieres anhand seiner ID ab.
- createTest: Erstellt einen neuen medizinischen Test und fügt ihn der Datenbank hinzu.
- createVaccine: Erstellt eine neue Impfung und fügt sie der Datenbank hinzu.
6. Benachrichtigungen (sendNotification, sendNotificationToUser, sendCancellationNotification, sendConfirmationNotification, getNotificationsByUserId, getNotificationsByType)
- sendNotification: Sendet eine Benachrichtigung und fügt sie der Datenbank hinzu.
- sendNotificationToUser: Sendet eine Benachrichtigung an einen bestimmten Benutzer mit einer Nachricht und einem Benachrichtigungstyp.
- sendCancellationNotification: Sendet eine Stornierungsbenachrichtigung an einen Benutzer.
- sendConfirmationNotification: Sendet eine Bestätigungsbenachrichtigung an einen Benutzer.
- getNotificationsByUserId: Ruft alle Benachrichtigungen für einen bestimmten Benutzer anhand seiner ID ab.
- getNotificationsByType: Ruft alle Benachrichtigungen eines bestimmten Typs ab.
7. Benutzer-Login (login)
- login: Authentifiziert einen Benutzer anhand seines Benutzernamens und Passworts.
8. Verwaltung von Terminen für spezifische Szenarien
- showUpcomingAppointments: Zeigt bevorstehende Termine für ein Haustier innerhalb der nächsten drei Tage an.
- cancelAppointmentsForVetInPeriod: Storniert alle Termine eines Tierarztes in einem bestimmten Zeitraum.
![abc drawio-2](https://github.com/user-attachments/assets/ca08e193-93bd-435e-8161-7fd08d95877c)

