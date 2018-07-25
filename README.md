# Demo project for evaluating relational and non relational databases using Spring MVC.

A demo project for evaluate different database technologies for a web application.

The project contains two maven modules, each one using Spring MVC framework. 
The spring-mvc-jpa module uses an in-memory H2 database whereas spring-mvc-mongodb uses MongoDB. Both databases are accessed using Spring repositories.

### Main technologies

- Spring Boot 2.0
- Spring MVC 5.0.3
- Spring security
- H2 database
- MongoDB database (external)

### Requisites
- Java JDK 1.8+
- Maven 3

### Build & run

In order to run the project, you need to execute the following commands.

#### Backend

Build:
```
./mvnw clean install
```
Run:
```
./mvnw spring-boot:run
```

The previous command will deploy the backend API into http://localhost:8080

#### Frontend
Build:
```
ng build
```
Run:
```
ng serve
```
The previous command will deploy the frontend application into http://localhost:4200
