# Quick Installation Guide

## Prerequisites (Server)
* Payara Server installiert
* PostgreSQL Server installiert
* PostgreSQL Treiber (postgresql-9.X.jar) in Payara 'glassfish/domains/domain1/lib/' kopieren
* PostgreSQL Datasource in Payara mit dem Namen 'jdbc/newsboard_readwrite' erstellen
* Pfad 'hibernate.search.default.indexBase' in persistence.xml anpassen auf beliebiges 
 existierendes Verzeichnis, für dass Schreibrechte durch den Benutzer der Payara ausführt 
 vorhanden sind

## Prerequisites (Development)
* Download & Install Gradle
* Download & Install Node.JS
* npm -g install bower

## Build
* ./gradlew build

## Deployment
* NewsBoard.ear auf Payara Server deployen

# Anleitung
Eine ausführlichere Anleitung und Hinweise sind in folgendem Wiki Eintrag zu finden.

http://git01-ifm-min.ad.fh-bielefeld.de/KI-Newsboard/Newsboard/2016_10_Modulares_WebNewsboard/wikis/how-to-install