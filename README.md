#  StockControl API

Backend RESTful API for managing products, raw materials, and production planning.

This system simulates an industrial stock control and production suggestion engine based on available raw materials.

---

##  Tech Stack

- Java 17
- Spring Boot 3
- Spring Data JPA
- PostgreSQL (Docker)
- Swagger / OpenAPI
- Maven
- JaCoCo (Code Coverage)
- JUnit
- Cypress-ready integration

---

##  Architecture

The project follows clean layered architecture:

```
com.vieri.stockcontrol
 ├── controller
 ├── service
 ├── repository
 ├── domain/entity
 ├── dto
 └── config
```

- **Controller** → HTTP entrypoints
- **Service** → Business logic
- **Repository** → Data access (JPA)
- **DTO** → Data transfer objects

---

##  Features

###  Products CRUD
- Create
- List
- Update
- Delete

###  Raw Materials CRUD
- Create
- List
- Update
- Delete

###  Product ↔ Raw Material Association
- Define required quantity per product

###  Production Plan Calculation

- Calculates how many units can be produced
- Prioritizes higher-value products
- Uses greedy strategy
- Calculates total revenue

---

##  Production Strategy

The system:

1. Orders products by highest price
2. Calculates max production based on raw material stock
3. Uses the minimum available constraint
4. Simulates stock consumption
5. Returns estimated revenue

---

##  Running the Application

### Start Database (Docker)

```bash
docker compose up -d
```

### Run Application

```bash
mvn spring-boot:run
```

Swagger available at:

```
http://localhost:8080/swagger-ui/index.html
```

---

## Testing

### Run Unit + Integration Tests

```bash
mvn test
```

### Code Coverage

JaCoCo report available at:

```
target/site/jacoco/index.html
```

---

## API Endpoint Example

```
GET /production-plan
```

Response:

```json
{
  "items": [
    {
      "productId": 1,
      "productName": "Table",
      "quantity": 10,
      "unitPrice": 350,
      "totalValue": 3500
    }
  ],
  "totalRevenue": 3500
}
```

---

##  Author

Vieri Costa de Oliveira

---

This project was developed as a technical challenge.