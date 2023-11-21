# Provinces
Dummy Spring boot based maven, WebFlux, Reactive RESTfull API (contrat last), Hateoas, Spring data r2dbc, java 11, Spring Webflux Security, Json Web Token (JWT), Mockito, project reactor test, jacoco, H2 docker and kubernetes project

# How to do
Clone the application and import it in your IDE. 
All GET requests are permitted without JWT authentication. 

For rapid testing, open your prefered Command Line Interface (CLI) and run : 
- mvn clean package to compile the application;

- mvn spring-boot:run' to run the app 
	
- Because all GET request are permitted, go to your prefered browser and type http://localhost:8888/olibillapi/v1/provinces or use your Commande Line Interface with this command 
  curl http://localhost:8888/olibillapi/v1/provinces | jq
  
  So you can get this result after authentification :
  
 [
  {
    "provinceName": "Alberta",
    "provinceCode": "AB",
    "links": [
      {
        "rel": "provinces",
        "href": "http://yourIpAdress:8888/provinces"
      },
      {
        "rel": "self",
        "href": "http://yourIpAdress:8888/provinces/AB"
      }
    ]
  },
  {
    "provinceName": "Ontario",
    "provinceCode": "ON",
    "links": [
      {
        "rel": "provinces",
        "href": "http://yourIpAdress:8888/provinces"
      },
      {
        "rel": "self",
        "href": "http://yourIpAdress:8888/provinces/ON"
      }
    ]
  },
  {
    "provinceName": "Québec",
    "provinceCode": "QC",
    "links": [
      {
        "rel": "provinces",
        "href": "http://yourIpAdress:8888/provinces"
      },
      {
        "rel": "self",
        "href": "http://yourIpAdress:8888/provinces/QC"
      }
    ]
  },
  {
    "provinceName": "Colombie-Britanique",
    "provinceCode": "BC",
    "links": [
      {
        "rel": "provinces",
        "href": "http://yourIpAdress:8888/provinces"
      },
      {
        "rel": "self",
        "href": "http://yourIpAdress:8888/provinces/BC"
      }
    ]
  },
  {
    "provinceName": "Ile-de-Prince-Édouard",
    "provinceCode": "PE",
    "links": [
      {
        "rel": "provinces",
        "href": "http://yourIpAdress:8888/provinces"
      },
      {
        "rel": "self",
        "href": "http://yourIpAdress:8888/provinces/PE"
      }
    ]
  },
  ...,
  {
    "provinceName": "Yukon",
    "provinceCode": "YT",
    "links": [
      {
        "rel": "provinces",
        "href": "http://yourIpAdress:8888/provinces"
      },
      {
        "rel": "self",
        "href": "http://yourIpAdress:8888/provinces/YT"
      }
    ]
  },
  {
    "provinceName": "Nunavut",
    "provinceCode": "NU",
    "links": [
      {
        "rel": "provinces",
        "href": "http://yourIpAdress:8888/provinces"
      },
      {
        "rel": "self",
        "href": "http://yourIpAdress:8888/provinces/NU"
      }
    ]
  }
 ]
  

- For POST requests, you have to get JWT token before
-- To get token, use this commande on your CLI
   $ curl -X POST http://localhost:8888/olibillapi/v1/auth/login -H "Content-Type:application/json" -d "{\"username\":\"dongongo\", \"password\":\"dongongo\"}"
 
   and, you get the access token in the head of the response : this is the value of field's Authorization. So you copie it before creating a new province.

 -- Create new data by typing this commande on your CLI :

 $ curl -X POST  -d '{"id":1200, "provinceName":"Other", "provinceCode":"OP"}' -H 'Authorization:Bearer xxx, Content-type:application/hal+json'  http://localhost:8888/olibillapi/v1/provinces | jq

this displays :

{
  "provinceName": "Other",
  "provinceCode": "OP",
  "_links": {
    "provinces": {
      "href": "http://172.30.238.111:8888/provinces"
    },
    "self": {
      "href": "http://172.30.238.111:8888/provinces/OP"
    }
  }
} 

- For DELETE AND PUT, you have to get JWT token before.
 

# Unit test
Two basics unit test classes for reactive controller and service are included.
The controller's test class shows how to use together StepVerifier from reactive-test and Mockito.

# H2 database 
The application create a H2 DB file in the root of the app : './olibilldb/olibillprovincedb.mv'.

# Logging file
The application create and/or stores the log file '/olibilllogs/province.log' on your local disk. 
