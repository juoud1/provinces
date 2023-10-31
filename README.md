# Provinces
Dummy Spring boot based maven, WebFlux, Reactive RESTfull API (contrat last), Hateoas, Spring data r2dbc, java 11, Mockito, project reactor test, jacoco, H2 docker and kubernetes project

# How to do
Clone the application and import it in your IDE. 

For rapid testing, open your prefered Command Line Interface (CLI) and run : 
- mvn clean package to compile the application;

- mvn spring-boot:run' to run the app 

- curl http://localhost:8888/provinces | jq
  and you get this result :
 [
  {
    "provinceName": "Alberta",
    "provinceCode": "AB",
    "links": [
      {
        "rel": "provinces",
        "href": "http://172.30.238.111:8888/provinces"
      },
      {
        "rel": "self",
        "href": "http://172.30.238.111:8888/provinces/AB"
      }
    ]
  },
  {
    "provinceName": "Ontario",
    "provinceCode": "ON",
    "links": [
      {
        "rel": "provinces",
        "href": "http://172.30.238.111:8888/provinces"
      },
      {
        "rel": "self",
        "href": "http://172.30.238.111:8888/provinces/ON"
      }
    ]
  },
  {
    "provinceName": "Québec",
    "provinceCode": "QC",
    "links": [
      {
        "rel": "provinces",
        "href": "http://172.30.238.111:8888/provinces"
      },
      {
        "rel": "self",
        "href": "http://172.30.238.111:8888/provinces/QC"
      }
    ]
  },
  {
    "provinceName": "Colombie-Britanique",
    "provinceCode": "BC",
    "links": [
      {
        "rel": "provinces",
        "href": "http://172.30.238.111:8888/provinces"
      },
      {
        "rel": "self",
        "href": "http://172.30.238.111:8888/provinces/BC"
      }
    ]
  },
  {
    "provinceName": "Ile-de-Prince-Édouard",
    "provinceCode": "PE",
    "links": [
      {
        "rel": "provinces",
        "href": "http://172.30.238.111:8888/provinces"
      },
      {
        "rel": "self",
        "href": "http://172.30.238.111:8888/provinces/PE"
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
        "href": "http://172.30.238.111:8888/provinces"
      },
      {
        "rel": "self",
        "href": "http://172.30.238.111:8888/provinces/YT"
      }
    ]
  },
  {
    "provinceName": "Nunavut",
    "provinceCode": "NU",
    "links": [
      {
        "rel": "provinces",
        "href": "http://172.30.238.111:8888/provinces"
      },
      {
        "rel": "self",
        "href": "http://172.30.238.111:8888/provinces/NU"
      }
    ]
  }
 ]
  

Also create new data by typing this commande on your CLI :

 $ curl -X POST  -d '{"id":1200, "provinceName":"Other", "provinceCode":"OP"}' -H 'Content-type:application/hal+json'  http://localhost:8888/provinces | jq

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

To delete a province or territory, type this :
 curl -X DELETE http://localhost:8888/provinces/OP | jq
 

# Unit test
Two basics unit test classes for reactive controller and service are included.
The controller's test class shows how to use together StepVerifier from reactive-test and Mockito.

# Logging file
The application create and/or stores the log file '/olibilllogs/province.log' on your local disk. 
