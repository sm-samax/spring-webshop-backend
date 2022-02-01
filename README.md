## Spring Boot Webshop-backend
---
<p>My project demonstrates how can be simply implemented a webshop-backend.</p>
<p>Spring Boot is being used in order to obtain the Restful approach of data processing.</p>

### Entities

<p>Main entities are Product and Purchase. They represent the functionality customers order from existing products</p>
<p>and the data is being stored as a Purchase. Auxiliary entities are shown bellow on the data model.</p>

### RestControllers

<p> The backend has two end-points:<p/>
<ul>
<li>ProductController (path: http://localhost:8080/api/products)
<li>PurchaseController (path: http://localhost:8080/api/purchases)
</ul>
<p>Both of them have the ability to cascade way store or delete object models.</p>
<p>Therefore using one of end-points can store the entire data model, if necessary, without creating duplicates</p>

### Data model
