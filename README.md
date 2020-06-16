#Person Detail app
This app developed using spring boot web applications, where user can perform following actions:
* Add/Edit/Delete Persons
* Add/Edit/Delete Address of Persons

### Person app design Assumptions
My Assumption about this coding exercise are below:
* I should provide UI to make some user actions like Add/Update/Delete operations.
* I should use in-memory database so that it can be easy test application without any external installation.
* Add unit test cases, due to time constraint I have added only few unit test cases(positive use cases) around 33.
* Not able to add proper exception handling and validations because of time.

### Technology used
* Spring-boot - for backend code(handle request/response and business logic).
* Thymeleaf & Bootstrap CSS - for frontend views
* H2 database - to store all user actions in database.
* Lombok - to generate getter/setter, constructors and builder methods in POJO classes.
* Spring Test, Mockito and Junit 5 - to write some unit test cases.


## Getting Started
To copy the project locally, run the following commands:

```
git clone https://github.com/bhanuGajjala/persondetailapp.git persondetailapp
cd persondetailapp
```

To install all of its dependencies and run unit test cases, follow the instructions below.

```
./mvnw clean install
```

To run application and deploy to embedded tomcat server, follow the instructions below.

```
./mvnw spring-boot:run
```

### Follow below instructions to verify application.

* Home page - `http://localhost:8080`
* Add Person - Click on the Add person tab on the nav bar.
* Add Address - After adding person, flow redirected to address page where we can add address/addresses.
* List Persons - Click on the Show person tab on the nav bar.
* Edit/Delete - Implemented two ways, 1. Can edit/delete using model pop up and added hyperlink edit/delete operations.
* H2 database url - `http://localhost:8080/h2-console`
* Copy H2 database connection url from logs Database available at `jdbc:h2:mem:d808df18-6e77-414d-bf0d-25a9c677147c`(This value will be changed every time we start application) and paste in `http://localhost:8080/h2-console` view page.



