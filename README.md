# Product Service

## Overview
Product Service is responsible for managing product catalog and inventory. It provides APIs to create, update, and query products with advanced filtering, sorting, and pagination.

This service is consumed by Order Service for product-related data.

---

## Features
- CRUD operations for products
- Pagination and sorting (price, product name)
- Filtering by price range and stock quantity
- Inventory tracking
- Input validation and exception handling

---

## Tech Stack
- Java 21
- Spring Boot
- Spring Data JPA
- MySQL
- Lombok
- Maven

---

## Base URL
http://localhost:3002/api/v2/products

---

## API Endpoints

### Create Product
POST /


### Get Product by ID
GET /{id}

### Get All Products
GET /?page=0&size=10&sortBy=price&direction=asc

### Filter Products
GET /?minPrice=1000&maxPrice=50000
GET /?stockQuantity=10


---

## Database
- Dedicated MySQL database
- Maintains product and inventory data independently

---

## Testing
- APIs tested using Postman

---

## Notes
- Stock is managed within this service
- Does not contain customer or order details

---

## Future Improvements
- Automatic stock reduction on order placement
- API Gateway integration
- Service discovery (Eureka)
- Unit testing
