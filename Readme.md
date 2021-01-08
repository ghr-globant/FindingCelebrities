# Find Celebriy App

## Purpose

This application has the purpose to show how the application finds a celebrity in different environments.
The celebrity used for this exercise is named "Michael Jackson" and there are some other people named John Doe, 
Sam Smith, Mark Doe and Martha Wilkinson.

The other people involved are related within each other, but the celebrity does not have any connections, 
that will be the key to find within the different teams that group people to find witch one is the celebrity.

## Scenarios

There are different scenarios in order to identify that the implemented logic does work for all the possible ones.

* A team with all the persons available, the celebrity, Sam Smith, Mark Doe and Martha Wilkinson
* A team with no members
* A team with only one member that is not the celebrity
* A team with only one member that is the celebrity
* A team with no celebrities 

## Technologies used

* Java 1.8
* Maven
* Lombok
* Junit

## How to compile

Because the application uses Maven, you can use maven lifecycles to get the application binaries.
You can use: ***mvn clean compile*** to get the binaries doing a cleanup in order to grantee no old classes will be included.

## How to run tests

In order to run the test scenarios you can use ***mvn test***

## How to run the application

The default packaging of the application is a JAR file, and you can get it using de maven command ***mvn package***
Once you've got the JAR file, you can use the Java command to run the application: **java -jar Celebrity-1.0-SNAPSHOT.jar**
