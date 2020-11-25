###### Assignment project - Budget Assistance 
Prototype for home budget assistant. Application is using maven, Spring Boot 2 and in
memory H2 database (saved to file in the same folder as application work dir). The database is being initially loaded with example 
data from src/main/resources/data.sql file (only first time). For testing purposes 
I've used the spock framework and swagger-ui for API documentation 
(available after starting application on http://localhost:8080/swagger-ui/) 

Application can be launch from any IDE supporting spring boot features or as any spring boot app
using maven `./mvnw spring-boot:run` please see https://spring.io/guides/gs/spring-boot/ for more info.

###### Available endpoints:
balance
GET  localhost:8080/v1/register/balance/{accountId}
recharge
POST localhost:8080/v1/register/recharge/{accountId}/{category}/{amount}
transfer
POST localhost:8080/v1/register/transfer/{accountId}/{source}/{target}/{amount}

###### Example data:
AccountId: 1
Register category: 'Wallet', 'Savings', 'Insurance policy' 'Food expenses')