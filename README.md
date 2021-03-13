# TicketSystem

REST service, which allows you to find tickets for concerts and sport events. Client chooses a desired ticket by date, event name or venue. 
The ticket can grant access to single event or numerous (season pass for example). One ticket can belog to one person or more (family ticket).

## Documentation
your-domain/swagger-ui

## Run steps
### Prerequisites
You should have Java 8 or higher, Apache Maven and PostgreSQL installed.

  * Create database schema by running src/main/resources/schema.sql and fill it with data in src/main/resources/data.sql
  * Build package by mvn clean package
  * Run with java - jar 
  * Open locahost:8080 in your browser or HTTP client
  * Enjoy ;)
