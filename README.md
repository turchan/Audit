# Audit
## Instructions for starting
### Run The Application

1) Create the db "test" to your mongodb on local server
2) Run the application using commands "mvn spring-boot:run"
3) The username for access to API is "admin". The password for access to API is "password"

#### OR 

1) Uncomment the resource for connection to mongodb service
2) Input command "docker-compose up" from the application directory
3) The username for access to API is "admin". The password for access to API is "password"

### Run the Tests
1) Create the db "test" to your mongodb on local server
2) Input command "mvn test" from application Directory when the application is running

### Error

In a case of some error try to input the line "mvn clean install"
