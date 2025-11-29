# Backend Test with RestAssured ![Java](https://img.shields.io/badge/Java-17-blue) ![Maven](https://img.shields.io/badge/Maven-3.9.1-red) ![Rest-Assured](https://img.shields.io/badge/Rest--Assured-5.4.0-green)

[![Build Status](https://img.shields.io/github/actions/workflow/status/erdoganp/backend-test-with-RestAssured/maven.yml?branch=main)](https://github.com/erdoganp/backend-test-with-RestAssured/actions)  
[![Allure Report](https://img.shields.io/badge/Allure-Report-blue)](target/allure-report/index.html)  

---

## Project Overview

This project is a **backend API automation test suite** using **Java, Maven, Rest-Assured, and JUnit 5**.  

It validates REST APIs for both **positive** and **negative scenarios**, including **CRUD operations, schema validation**, and **error handling**.  



---

## Technologies Used

- **Java 17**  
- **Maven 3**  
- **Rest-Assured 5.4.0**  
- **JUnit 5**  
- **JSON Schema Validation**  
- **Allure Reporting**  
- **Git / GitHub**  
- **CI/CD**: GitHub Actions

---

## Project Structure
backend-test-with-RestAssured/
├─ pom.xml
├─ src/test/java/
| ├─ base/ # BaseTest class
│ ├─ models/ # Request & Response POJO classes
│ ├─ tests/ # Positive, negative, edge case tests
│ └─ clients/ # general Methods
├─ src/test/resources/
│ └─ schemas/ # JSON Schema files
└─ .github/workflows/ # GitHub Actions CI pipeline


---

## Test Scenarios

### Positive Tests
- Get product by valid ID → Validate status code, response fields
- Create, Update, Delete product → Validate request & response
- Schema validation for response contract

### Negative Tests
- Get product with invalid/non-existing ID → Expect 404
- Create product with missing/invalid fields → Expect 400
- Delete product with invalid ID → Validate proper error response

### Assertions & Validation
- Field-level checks: `id`, `title`, `price`, `description`, `category`  
- JSON Schema validation for response structure  
- HTTP status code validation (200, 404, 400, etc.)  

---

## POJO Examples

### Request POJO (`ProductRequest.java`)
```java
@Data
public class CreateProductRequest {
    private String title;
    private double price;
    private String description;
    private String category;
}




### Request POJO (`ProductResponse.java`)
```java
@Data
public class CreateProductRequest {
    private int id;
    private String title;
    private double price;
    private String description;
    private String category;
}





