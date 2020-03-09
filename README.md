#exchange-service-test
Test service for currencies exchange

##How to run

To run the application, execute:

    mvn package && java -jar target/exchange-0.0.1-SNAPSHOT.jar

##How to build

To build application jar with all dependencies inside run:

    mvn package

This will create `exchange-0.0.1-SNAPSHOT.jar` under build/libs directory

##Swagger

    1. http://localhost:8080/swagger-ui.html - UI for testing
    
##Database

	http://localhost:8080/h2-console/

	- JDBC URL: jdbc:h2:mem:testdb
	- login: sa
	- password: sa

##Users

1) Admin - all rights.

	- login: admin
	- password: qazwsxedcrfv

1) User 
	- GET  /api/commissions
	- GET  /api/exchange-rates
	- POST /api/exchange	

	- login: user
	- password: qwerty+1
