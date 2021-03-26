# Note

Note application is webservice which accept HTTP calls for CRUD notes. API is responsible for managing and storing in H2 database notes.
Application is using Java 11, Spring Boot, Spring Data JPA, Spring Envers, Maven, Lombok, H2, Swagger, React Js

## Installation
In the beggining of instalation you need to install java 11+ and maven

[Maven](https://maven.apache.org/download.cgi)

[Java 11](https://adoptopenjdk.net/)


## Running the service 
First step in clone the Git repository:

$ git clone https://github.com/AnnaPostek/Note
$ cd Note

Once the clone is done, run below

$ mvn -pl persistence spring-boot:run
$ cd target
$ java -jar notes-0.0.1-SNAPSHOT.jar

## Web
Application is available on below localhost:
http://localhost:8080/

## Login to database
Database H2 in memory is available on below localhost:
http://localhost:8080/h2-console

add date according following:

![image](https://user-images.githubusercontent.com/56793192/112542027-5f740200-8db4-11eb-9bd9-47f63cd7cc7c.png)

click Connect and after that you are connecing to database.

on database loading initial data according below:
![image](https://user-images.githubusercontent.com/56793192/112544040-d9a58600-8db6-11eb-8ce2-d50019f62540.png)

## Testing the API 
Below is more details examples of usages API

Swagger is available for below localhost:
http://localhost:8080/swagger-ui.html#/

## Available Endpoints
![image](https://user-images.githubusercontent.com/56793192/112544683-9c8dc380-8db7-11eb-8f41-d18a6d73db40.png)

## Examples usages
It is possible for testing API in Swagger, Postman, curl and endpoints with GET Mapping directly on browser

## Add note
require values is title and content. 
Date modification, Date creation and id is generated automatically.

### Add note in Swagger: 
click on note-controller then POST and Try it out, fill the values and click Execute. Note is added to database.
![image](https://user-images.githubusercontent.com/56793192/112546801-39516080-8dba-11eb-9f51-d5a7b4d3a048.png)

### Add note in Postman:
![image](https://user-images.githubusercontent.com/56793192/112609260-9fba9b00-8e1b-11eb-971b-713b1e7a334b.png)

### Add note in curl:
curl -X POST "http://localhost:8080/note" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"content\": \"Java\", \"title\": \"SpringBoot\"}"

Example of response above is according below:
![image](https://user-images.githubusercontent.com/56793192/112611361-017c0480-8e1e-11eb-9af9-bf2ed349b7db.png)

## Update note
on Swagger need to complete id, and change values of content and title. Modification date is updated automatically.
![image](https://user-images.githubusercontent.com/56793192/112547944-be894500-8dbb-11eb-9a20-4c4c1b7dce24.png)

## Delete note
Below is example of usage delete endpoint on Postman

![image](https://user-images.githubusercontent.com/56793192/112611070-a77b3f00-8e1d-11eb-91c0-ddb3d3a6ff72.png)


## Get note and Get all notes
Below is example of usage note and list of notes directly on browser:

![image](https://user-images.githubusercontent.com/56793192/112610618-2f147e00-8e1d-11eb-8a7f-ba9d7ecaddc2.png)

![image](https://user-images.githubusercontent.com/56793192/112610730-4ce1e300-8e1d-11eb-878c-8b3dcfbb70ec.png)


## Revision notes
This endpoint return whole history of changes for particulary note
curl -X GET "http://localhost:8080/notes/1/revisions" -H "accept: */*"

example of revisions note, when the last one used delete note:
![image](https://user-images.githubusercontent.com/56793192/112611724-7c451f80-8e1e-11eb-9860-92dec38df485.png)

## Frontend
In the project directory, you can run:

npm start
Runs the app in the development mode.
Open http://localhost:3000 to view it in the browser.

The page will reload if you make edits.

