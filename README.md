# Provinces
Dummy Spring boot, WebFlux, Spring data jpa, java 8, Mockito, project reactor test, docker and kubernetes project

# How to use
Clone the application, import it in your IDE. You can use maven command 'mvn clean package' to compile this application.
Run the application using the command 'mvn spring-boot:run' and go to your favorite browser or postman tool to check http://localhost:8888/provinces, this displays :
{"id":"AB","provinceName":"Alberta","provinceCode":"AB","new":false}
{"id":"BC","provinceName":"Colombie-Britanique","provinceCode":"BC","new":false}
{"id":"PE","provinceName":"Île-de-Prince-Édouard","provinceCode":"PE","new":false}
{"id":"MB","provinceName":"Manitoba","provinceCode":"MB","new":false}
{"id":"NB","provinceName":"Nouveau Brunswick","provinceCode":"NB","new":false}
{"id":"NS","provinceName":"Nouvelle Écosse","provinceCode":"NS","new":false}
{"id":"ON","provinceName":"Ontario","provinceCode":"ON","new":false}
...

# Unit test
Two basics unit test classes for reactive controller and service are included.
The controller's test class shows how to use together StepVerifier from reactive-test and Mockito.

# Logging file
The application create and/or stores the log file '/olibilllogs/province.log' on your local disk. 
