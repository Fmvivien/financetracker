# Finance Tracker - Corporate Loan Product Catalog API

A Spring Boot-based RESTful backend API designed for the structured management, tracking, and risk-based filtering of corporate loan products (e.g., Széchenyi Card programs, state-guaranteed loans).

**The primary objective of this project** is to merge my 10+ years of domain knowledge in **Corporate Banking, KYC, and Compliance** with modern enterprise software engineering and relational database practices.

---

## 🎯 Key Features & Business Logic

- **Structured Loan Product Model:** Comprehensive management of financial entities (including credit types, maximum loan amounts, product names, and audit timestamps).
- **Risk & Product Filtering:** Dynamic data manipulation and server-side filtering via REST endpoints.
- **Automated Data Initialization:** The system checks the persistence layer upon startup and automatically populates the database with real-world market configurations if empty.

---

## 🛠️ Tech Stack

- **Backend:** Java 21, Spring Boot 3.x
- **Data & Persistence:** Spring Data JPA, Hibernate ORM
- **Database:** MySQL (Production-ready relational integration)
- **Quality Assurance & Testing:** JUnit 5, Mockito (Robust isolation and mock architecture)
- **Project Management:** Maven, Git / GitHub

---

## 📐 Architecture & Clean Code

The project strictly follows the **Layered Architecture** pattern and adheres to **SOLID** principles to ensure enterprise-grade maintainability:

- **Controller Layer:** Exposes the REST API endpoints and handles incoming client HTTP requests.
- **Service Layer (Business Logic):** Completely decouples business operations from the controllers, handling calculations and data mutations.
- **DTO Pattern (Data Transfer Objects):** Implemented to secure internal database entities (`LoanProduct`) and encapsulate API data exposure.
- **Automated Testing:** Achieved **100% Line Coverage** for key service and controller components using automated Mockito unit tests with the *Given-When-Then* structure.
- **Security Best Practices:** Sensitive credentials (like database passwords) are externalized using **Environment Variables**, preventing critical leaks into the public repository.

---

## 🚀 Local Setup & Installation

1. **Clone the repository:**
   ```bash
   git clone https://github.com
   ```

2. **Database configuration:**
   Create a local MySQL schema named `financetracker`.

3. **Set Environment Variables:**
   Configure your local system or IDE run configurations to include:
   ```properties
   DB_PASSWORD=your_local_mysql_password
   ```

4. **Run the application:**
   Execute the following command in the project root:
   ```bash
   ./mvnw spring-boot:run
   ```