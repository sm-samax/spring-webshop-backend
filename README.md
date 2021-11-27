The project represents a simple example of how a webshop backend could be implemented with Spring Boot (v2.5.3).

Main goals:
- Receive the newly created products and store them on the data base. The product also can have a price rule and at least one tag.
- If requested, return the stored product objects to the frontend. Depending on request, the data can be filtered.

Layers of realization:
	API: ProductController (in com.samax.tech.webs.comp)
	Business: ProductService (in com.samax.tech.webs.service)
	Persistence: ProductRepository (in com.samax.tech.webs.repos)
	Database: H2 database (imported with Maven)

Entities:
	Their corresponding java files are in com.samax.tech.webs.entity package:
	Product - main entity with the following columns: (Optional): Description, thumbnail image URL, images URL and pricerule;
							  (Mandatory): Name, price, tags, amount left, popularity.
	PriceRule - auxiliary entity, serves to alternate the current price of a product entity.
		    Columns of it: (Mandatory): Reduction amount, active.
	Tag - auxiliary entity, serves as marker in the product makeing filtering easier.
	      It has one mandatory column: tag name.

	Every entity has a generated unique id. A data model, made with Oracle SQL Developer, can be found in the project under the "data_model" folder.

Tools being included:
	- Hibernate
	- Apache Tomcat
	- H2 Database
	- JUnit

Changes in the future:
- Adding appropriate handlers to ProductController
- Adding DTO-s
- Improve the project's security with Spring Security