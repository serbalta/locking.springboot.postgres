# Projekt: Locking

Dieses Projekt befasst sich mit der Synchronisation von Geschäftsfällen (Pessimistic/Optimistic locking) und dient als Übung für Interessierte.

## Anforderung (Anwendungsfall):
Eine API bietet Buchungen an (Bookings). Diese reservieren eine bestimmte Unterkunft (RoomId) für einen bestimmten Zeitraum. Es muss in jedem Fall verhindert werden, dass eine Buchung mehrfach abgeschlossen werden kann oder dass sich eine neue Buchung mit einer bereits bestehenden überschneidet. Die aufrufenden Clients der API sind dem System nicht bekannt und es kann daher nicht angenommen werden, dass diese eine Sychronisation umsetzen.
Die API bietet für den Abschluss einer Buchung drei Endpunkte an (POST Booking, POST BookingConfirmation, DELETE Booking). Damit kann ein Client einen zweistufigen Umsetzungsprozess abbilden. Hier der gut Fall mit beliebiger (für diese Übung nicht interessanter) clientseitigen Logik:
    1) Buchung reservieren (POST Booking)
    2) Clientseitige Logik (e.g.: Geldbuchungen) durchführen
    3) Buchung bestätigen (POST BookingConfirmation)
Sollte nach dem 2. Schritt der Client die Buchung nicht mehr abschließen wollen, dann gibt es die Möglichkeit die Buchung zu stornieren (DELETE Booking).
Man kann nun mittels Optimistic- oder Pessimistic-Locking den Gescahäftsfall synchronisieren. Die APIs sind aus Gründen der Performance and Ausfallsicherheit auf mindestens 2 Instanzen hoch skaliert weswegen aufeinanderfolgende Request, die eine zusammengehörige Reservierung und Bestätigung umsetzen, von unterschiedlichen Instanzen gehandelt werden können (load balancing passiert via round robin, keine sticky Sessions).

## Umsetzung:
In einer Gruppenarbeit wird die API mit den benötigten Endpunkten entwickelt. Der Status muss in einer Datenbank gehalten werden, sodass mehrere Instanzen der API synchronisert werden könnten. Für das Beispiel reicht es aus, dass die API und die verwendete DB in einem Docker Compose File kombiniert werden.
Imlpementieren sie je eine Version mit Optimistic- und eine Version mit Pessimistic-Locking. Die Endpunkte für Pessimistic- und Optimistic-Locking können in einer Api kombiniert sein und durch subrouten (e.g.: ../api/pessimistic/booking,  ..api/optimistic/booking) unterschieden werden.
Die Wahl der Technologie (Programmiersprache, DB) ist der Gruppe selbst überlassen.

Als Beispiel für eine Implementierung gibt es in diesem Repository eine Spring Boot API mit einer PostgreSql DB.
