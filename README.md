# KI-Newsboard
In diesem Projekt geht es darum, ein modulares webbasiertes System zur 
Anzeige der Suchergebnisse (Neuigkeiten mit Bewertung mit Hilfe der Sentimentanalyse) zu realisieren.

## Prerequisites (Server)
* Payara Server installiert
* PostgreSQL Server installiert
* PostgreSQL Treiber (postgresql-9.X.jar) in Payara 'glassfish/domains/domain1/lib/' kopieren
* PostgreSQL Datasource in Payara mit dem Namen 'jdbc/newsboard_readwrite' erstellen
* Pfad 'hibernate.search.default.indexBase' in persistence.xml anpassen auf beliebiges 
 existierendes Verzeichnis, für dass Schreibrechte durch den Benutzer der Payara ausführt 
 vorhanden sind

## Prerequisites (Development)
* Download & Install IntelliJ IDEA
* Download & Install Node.JS
* npm -g install bower

## Build
* cd de.fhbielefeld.scl.KINewsBoard.Web
* bower install
* Build Artifacts in IntelliJ

## Deployment
* NewsBoard.war auf Payara Server deployen