# belong-phonebook
========================================
To Run Locally:
  * mvn clean package
    * This will build the app and run integration tests
  * mvn spring-boot:run
    * This would start the application on localhost:8080

========================================
#API Description
The swagger API documentation URL.
http://localhost:8080/v2/api-docs

#Swagger UI
The swagger UI URL.
http://localhost:8080/swagger-ui/


#Available Rest Endpoints:
Phone Rest API
  * http://localhost:8080/api/phones
    * returns list of all phones
  * http://localhost:8080/api/phones/activateById/{phoneId}
    * activates phone, a phone ID to be provided. 
      * If phone already activated an error is returned:
        * {
          "url": "http://localhost:8080/api/phones/activateById/1",
          "ex": "Phone 0404000001 is already activated"
          }
      * If phone with passed ID is not found an error is returned:
        * {
          "url": "http://localhost:8080/api/phones/activateById/111",
          "ex": "Phone with id 111 does not exists"
          }
  * http://localhost:8080/api/phones/activate/{phoneNo}
    * activates phone, a phone number to be provided.
      * If phone already activated an error is returned:
        * {
          "url": "http://localhost:8080/api/phones/activate/0404000001",
          "ex": "Phone 0404000001 is already activated"
          }
      * If phone with passed ID is not found an error is returned:
        * {
          "url": "http://localhost:8080/api/phones/activate/0404000000",
          "ex": "Phone with number 0404000000 does not exists"
          }
          
          
Customer Rest API
  * http://localhost:8080/api/customers
    * returns list of all customers
  * http://localhost:8080/api/customers/phones/{customerId}
    * returns list of phone numbers for a customer, a customer ID to be provided
