# Asynchronous and scheduling Territory
Dummy Spring boot, WebFlux, Spring asynchronous TaskExecutor, Spring TaskScheduling, Spring data jpa, java 8, Mockito, project reactor test, docker and kubernetes project

# How to use
Clone the application, import it in your IDE. You can use maven command 'mvn clean package' to compile this application.
Run the main class as a java application so it prints out the list of 3 territories in Canada like :
Province(provinceName=Nunavut, provinceCode=NU) at 2019-12-17T23:59:37.957Z
Province(provinceName=Térritoires du Nord-Oeust, provinceCode=NT) at 2019-12-17T23:59:37.957Z
Province(provinceName=Yukon, provinceCode=YT) at 2019-12-17T23:59:37.957Z

...

# Unit test
Two basics unit test classes for reactive controller and service are included.
The controller's test class shows how to use together StepVerifier from reactive-test and Mockito.

No unit test classes for the scheduling and asynchronous TaskExecutor components.

# Logging file
The application create and/or stores the log file '/olibilllogs/province.log' on your local disk. 
