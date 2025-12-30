```md
# Loan API Demo – Spring Boot Assignment

## 📌 Overview
This project is a Spring Boot REST API developed as part of an API Development assignment.  
The application integrates with an external Loan Account API, persists the response into a MySQL database, and exposes a clean REST endpoint to consumers.

---

## 🧩 Features
- Integrates with external API:
```

[https://demo9993930.mockable.io/loanaccount/{loanAccountNumber}](https://demo9993930.mockable.io/loanaccount/{loanAccountNumber})

```
- Exposes a GET API to fetch loan details by loan account number
- Extracts **due EMI** details from the external response
- Persists loan data into MySQL database
- Returns a simplified response format as required
- Clean layered architecture (Controller, Service, Integration, Repository)
- Logging added for debugging and traceability
- SSL handling utility for external HTTPS calls

---

## 🛠️ Tech Stack
- Java 17+
- Spring Boot
- Spring Web
- Spring Data JPA (Hibernate)
- MySQL
- RestTemplate
- Lombok
- Maven

---

## 📂 Project Structure
```

src/main/java/com/example/loan_api_demo
│
├── controller
│   └── LoanController.java
│
├── service
│   └── LoanService.java
│
├── integration
│   └── LoanIntegrationClient.java
│
├── dto
│   ├── ExternalLoanResponseDto.java
│   └── LoanResponseDto.java
│
├── entity
│   └── LoanAccountEntity.java
│
├── repository
│   └── LoanRepository.java
│
├── util
│   └── SSLUtil.java
│
└── LoanApiDemoApplication.java

```

---

## 🔗 API Flow
1. Client calls:
```

GET /loan/{loanAccountNumber}

```
2. Controller delegates request to Service layer
3. Service calls external Loan API using Integration Client
4. EMI details are processed to find the **due EMI**
5. Data is saved into MySQL database
6. Response is returned in required format

---

## 📥 API Endpoint

### Get Loan Details
**Request**
```

GET [http://localhost:8080/loan/1](http://localhost:8080/loan/1)

````

**Response**
```json
{
  "loanAccountNumber": "1",
  "dueDate": "2024-04-01",
  "emiAmount": 10000.0
}
````

---

## 🗄️ Database Schema

```sql
CREATE TABLE loan_account (
    id INT AUTO_INCREMENT PRIMARY KEY,
    loan_account_number VARCHAR(50),
    due_date DATE,
    emi_amount DECIMAL(10,2)
);
```

---

## ⚙️ Configuration

Update `application.properties` with your MySQL credentials:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/loan_db_demo
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

## 🔐 SSL Handling

Some external HTTPS APIs may cause SSL handshake issues in local environments.
`SSLUtil` is used to bypass SSL validation **only for local/testing purposes** to ensure smooth API integration.

---

## ▶️ How to Run

1. Clone the repository
2. Create MySQL database `loan_db_demo`
3. Update database credentials
4. Run:

   ```bash
   mvn spring-boot:run
   ```
5. Test API using Postman or browser

---

## 🧪 Testing

* Tested using Postman
* Verified database persistence via MySQL Workbench
* Logs enabled for API calls and DB operations

