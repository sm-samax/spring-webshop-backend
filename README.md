The project represents a simple example how a webshop backend could be implemented with Spring Boot.
I used 2.5.3 version of the framework.

Main goals:
- Receive the newly created products and store them on the data base.
- If requested, return the stored product objects to the frontend. Depending on request, the data can be filtered.

Realization:
- Save the new products:
	The ProductController class provides endpoints and makes possible the data flow through HTTP methods.
	The incoming data are recognised as Product objects. They have some mandatory fields like: id, name, price, tags.
	The second layer of work is the ProductService class. It evaluates whether the incoming data should be stored in the data base or not.
	Finally the service 


Tools being included:
	- Hibernate
	- Apache Tomcat
	- H2 Database
	- JUnit