# Price Offer Calculator

## Overview
The **Price Offer Calculator** is an application designed to automate the process of calculating price offers, formatting text, and storing orders in a database. It is built using **Spring Boot** and **PostgreSQL**, leveraging **Docker** for containerized deployment.

## Features
- Automated price offer calculation based on various parameters  
- Text formatting for generating structured price offers  
- Order management with database persistence  
- Template creation to simplify repetitive tasks  
- REST API for external interaction  
- Docker support for easy deployment  

---

## Technologies Used
- **Spring Boot** – Backend framework  
- **PostgreSQL** – Database management  
- **Docker & Docker Compose** – Containerization & deployment  
- **Maven** – Dependency management  

---

## Setup Instructions

### **Prerequisites**
Ensure you have the following installed:  
- **Java 21**  
- **Maven**  
- **Docker & Docker Compose**  
- **PostgreSQL** (if running without Docker)  

---

### Prerequisites
Ensure you have the following installed:
- Java 21
- Maven
- Docker & Docker Compose
- PostgreSQL (if running without Docker)
  

### Installation Steps
1. **Clone the Repository**  
   Clone the repository to your local machine:
   ```bash
   git clone <repository-url>
   cd price-offer-calculation
   

2. Build the Application
   ```bash
    mvn clean package
   

4. Run with Docker
Use docker-compose to start the application along with the database:
   ```bash
    docker-compose up --build
   

6. Run without Docker
If you prefer to run the application manually:
- Start PostgreSQL and create the necessary database.
- Update application.properties with your PostgreSQL credentials or set up environment variables. 
- Run the application:
    ```bash
  mvn spring-boot:run
or
- Run the built .jar file:
     ```bash
  java -jar target/calculator-0.0.1.jar
     

## Documentation
- This application includes an API documentation powered by **Swagger UI**.
1. Run the application
2. Open the URL:
     ```bash
      http://localhost:8080/swagger-ui/index.html
     

## Environment Variables
You can configure the application using environment variables:
- SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/your_database
- SPRING_DATASOURCE_USERNAME=your_username
- SPRING_DATASOURCE_PASSWORD=your_password

  ---

## Planned Features
- Database migration for better schema versioning
- Add more unit and integration tests to improve application reliability
- Generating price offers in PDF format
- Integration with email drafts to send offers directly via email
- Price breakdowns for more detailed cost analysis
- Ability to change item prices in the database
- Stock management for items
- Currency changes feature
