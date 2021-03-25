# Note

Note application is webservice which accept HTTP calls for CRUD notes. API is responsible for managing and storing in H2 database notes.
Application is using Java 11, Spring Boot, Spring Data JPA, Spring Envers, Maven, Lombok, H2, Swagger

## Installation
In the beggining of instalation you need to install java 11 and maven

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

on database inserted below records:
![image](https://user-images.githubusercontent.com/56793192/112544040-d9a58600-8db6-11eb-8ce2-d50019f62540.png)

## Testing the API 
It is possible for testing the API in three ways: Postman, Swagger and curl. 
Below is more details examples of usages API

Swagger is available for below localhost:
http://localhost:8080/swagger-ui.html#/


## Available Endpoints
![image](https://user-images.githubusercontent.com/56793192/112544683-9c8dc380-8db7-11eb-8f41-d18a6d73db40.png)

## Examples usages
It is possible for testing API in Swagger, Postman and curl

## Add note
require values is title and content. 
Date modification, Date creation and id is generated automatically.
### Add note in Swagger: 
click on note-controller then POST and Try it out, fill the values and click Execute. Note is added to database.
![image](https://user-images.githubusercontent.com/56793192/112546801-39516080-8dba-11eb-9f51-d5a7b4d3a048.png)

### Add note in Postman:
![image](https://user-images.githubusercontent.com/56793192/112547293-e0ce9300-8dba-11eb-82b7-6ace1c77fe4c.png)

### Add note in curl:
curl -X POST "http://localhost:8080/note" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"content\": \"Java\", \"title\": \"SpringBoot\"}"

## Update note
on Swagger need to fill in id, and change values of content and title. Modification date is updated automatically.
![image](https://user-images.githubusercontent.com/56793192/112547944-be894500-8dbb-11eb-9a20-4c4c1b7dce24.png)

## Revision notes
This endpoint return whole history of changes for particulary note
curl -X GET "http://localhost:8080/notes/1/revisions" -H "accept: */*"

example of revisions note, when the last one used delete note:
[
    {
        "id": 1,
        "title": "Java Learning",
        "content": "Spring Boot",
        "created": "2021-03-20 12:00:00",
        "modified": "2021-03-25 22:46:14"
    },
    {
        "id": 1,
        "title": "Spring Boot",
        "content": "Java",
        "created": "2021-03-20 12:00:00",
        "modified": "2021-03-25 22:53:46"
    },
    {
        "id": 1,
        "title": "Spring",
        "content": "Java",
        "created": "2021-03-20 12:00:00",
        "modified": "2021-03-25 22:53:53"
    },
    {
        "id": 1,
        "title": null,
        "content": null,
        "created": null,
        "modified": null
    }
]


The rest endpoints can be tested accordingly above

