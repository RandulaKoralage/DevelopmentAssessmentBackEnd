# Development Assessment - BackEnd
Price engine and price calculator

## Requirements
* [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* [MySQL 8.0](https://dev.mysql.com/downloads/mysql/)

## Running the Application Locally
Setup a dummy database and edit database variables in `application.properties` file

One way is to execute the main method from the IDE ([inteliJ IDEA](https://www.jetbrains.com/idea/download/))

or use Spring Boot Maven plugin and execute
`mvn spring-boot:run`

## Development Tools/Technologies

## Server Side
* [Spring Boot](https://spring.io/projects/spring-boot)
* [Maven](https://maven.apache.org/)
* [jUnit](https://junit.org/junit5/)
* [MySQL](https://dev.mysql.com/downloads/mysql/)

## Libraries and Plugins
* [Lombok](https://projectlombok.org/)
* [Model Mapper](http://modelmapper.org/)
* [JDBC Driver for MySQL](https://www.mysql.com/products/connector/)

## IDEs and Tools
* [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* [MySQL Workbench](https://www.mysql.com/products/workbench/)
* [Postman](https://www.postman.com/)
* [SourceTree](https://www.sourcetreeapp.com/)

***********************************************************
## The Project Architecture

<img src="https://i.imgur.com/ouo43GN.png" title="source: imgur.com" />

## Postman Tests
http://localhost:8080/items/
<img src="https://i.imgur.com/f2KEoO2.png" title="source: imgur.com" />

http://localhost:8080/price_list?id=2
<img src="https://i.imgur.com/uKP9JnN.png" title="source: imgur.com" />

http://localhost:8080/price
<img src="https://i.imgur.com/ZViBHKv.png" title="source: imgur.com" />
