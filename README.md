# library-management-hexad

### How to run the unit tests

Navigate inside the backend project

```
cd library-management-backend
```

Now run ``mvn test``. You are likely to see the following output in the console if all the tests are successful.
![unit test success console output](images/mvn-test-success.png)

Click on the link, and it will take you to the report of the tests in cucumber site.

### Running the application server

From the ``library-management-backend`` run the command ``mvn spring-boot:start`` in the command line. It will start the application.

##### Swagger Endpoint

Go to browser and in the address bar go to http://localhost:8080/api/swagger-ui/index.html

This will bring you a very minimal swagger ui.

![Swagger UI](images/swagger-ui.png)

### Running the web server

Navigate to the ``library-management-frontend`` module and run the command `ng serve`. It will take to the book adding form.

![Add book](images/add-book.png)