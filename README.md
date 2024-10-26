# Product Management for an eCommerce Platform (Following DDD Principles)

## Project Overview
This project is a Product Management RESTful API designed for an eCommerce platform, developed using Java and Spring Boot, and structured according to Domain-Driven Design (DDD) principles. It enables operations like product creation, updating, stock management, and category assignments.

## Tools & Technologies Used
- IDE: IntelliJ IDEA
- Database: H2 In-Memory Database
- API Testing: Postman
- Version Control: Git
- Build Tool: Maven
- Programming Language: Java (JDK 17)
- Framework: Spring Boot (Version 3.2.4)

## Project Structure
The project follows a layered architecture:

- common: Contains shared resources across the project.

  - dto: Defines common Data Transfer Objects (DTOs)
  - entity: Base entities such as BaseEntity.
  - exceptions: Contains custom exceptions like ProductNameAlreadyTakenException and a global handler (GlobalExceptionHandler).

- product: Contains domain logic, application services, and API controllers.

  - application: Contains the REST API for managing products.
      - Controller: ProductController - handles HTTP requests for product operations.
      - Service: ProductService - holds the core business logic for products.
  - domain: Contains the domain-specific logic for products.
      - DTO: Data Transfer Objects for handling API responses, including ProductDTO, UpdateStockDTO, and PaginatedProductResponse.
      - Entity: Defines core entities like Product and Category.
  - event: Domain events related to product changes, such as ProductCreatedEvent and ProductStockUpdatedEvent.
 
## Database Configuration
The API uses an H2 in-memory database for storing product data. This database is configured for local testing and will be reset each time the application restarts.

  - Database Console: Accessible at http://localhost:9191/h2-console
  - JDBC URL: jdbc:h2:mem:testdb
  - Username: sa
  - Password: password

## API Endpoints
### Product Management
- GET /product/getAllProduct?page={{$random.integer(100)}}&size={{$random.integer(100)}}&sortBy={{$random.alphanumeric(8)}}) - Retrieve all products (supports pagination).
- GET /product/{id} - Retrieve a product by its unique ID.
- POST /product/add_product - Create a new product.
- PUT /products/updateProduct/{id} - Update details of an existing product by ID.
- DELETE /product/deleteProduct/{id} - Delete a product by ID.
- PATCH /product/{id}/update-stock - Update the stock quantity of a product.
- PATCH /product/{{id}}/apply-discount?discount={{$placeholder}} - Apply Discount to a product.

## API DOCUMENTATION
### POSTMAN API DOC: https://documenter.getpostman.com/view/26556785/2sAY4sj4uv
