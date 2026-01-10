# 📚 Rent Read – Book Rental Application

Rent Read is a **Spring Boot–based REST API** for managing a book rental system with **JWT authentication**, **role-based access control**, and **Swagger API documentation**.

---

## 🚀 Features

- 🔐 User Signup & Login (JWT Authentication)
- 👥 Role-based Authorization (USER / ADMIN)
- 📖 Book Management (CRUD operations)
- ✅ View available books
- 🧾 Global exception handling
- 📘 Swagger UI for API testing
- 🗄️ MySQL database integration

---

## 🛠️ Tech Stack

- **Java 21**
- **Spring Boot 3.4.1**
- **Spring Security**
- **Spring Data JPA (Hibernate)**
- **JWT Authentication**
- **MySQL**
- **Swagger / OpenAPI (springdoc-openapi)**
- **Gradle**

---

## ⚙️ Project Setup

### 1️⃣ Clone the repository
```bash
git clone https://github.com/akash868/Rent_read-application.git
cd Rent_read-application
````

---

### 2️⃣ Configure Database

Update `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/test_db
spring.datasource.username=assessment
spring.datasource.password=redrum

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

Ensure MySQL is running and the database exists.

---

### 3️⃣ Run the application

```bash
./gradlew bootRun
```

Application runs at:

```
http://localhost:8081
```

---

## 📘 Swagger API Documentation

Access Swagger UI at:

```
http://localhost:8081/swagger-ui/index.html
```

Use Swagger to:

* Signup users
* Login and get JWT token
* Authorize using **Bearer Token**
* Call secured APIs

---

## 🔐 Authentication Flow

### 1️⃣ Signup

`POST /auth/signup`

```json
{
  "email": "user@example.com",
  "password": "password123"
}
```

---

### 2️⃣ Login

`POST /auth/login`

```json
{
  "email": "user@example.com",
  "password": "password123"
}
```

Response:

```json
{
  "token": "JWT_TOKEN_HERE"
}
```

---

### 3️⃣ Authorize in Swagger

Click **Authorize 🔐** and enter:

```
Bearer JWT_TOKEN_HERE
```

---

## 📚 Book APIs

| Method | Endpoint         | Access        |
| ------ | ---------------- | ------------- |
| GET    | /books           | Authenticated |
| GET    | /books/available | Authenticated |
| POST   | /books           | ADMIN         |
| PUT    | /books/{id}      | ADMIN         |
| DELETE | /books/{id}      | ADMIN         |

---

## ⚠️ Error Handling

Global exception handling using `@RestControllerAdvice`:

* 400 – Bad Request
* 403 – Forbidden
* 404 – Not Found
* 500 – Internal Server Error

---

## 📁 Project Structure

```
src/main/java/com/crio/rent_read
├── controller
├── service
├── repository
├── entity
├── dto
├── security
├── exception
└── config
```

---

## 👨‍💻 Author

**Akash (akash868)**
GitHub: [https://github.com/akash868](https://github.com/akash868)

---

## 📄 License

This project is for educational and assessment purposes.

```

---

### ✅ Next optional improvements
If you want, I can:
- Add **API examples screenshots**
- Improve **Swagger annotations**
- Add **Docker support**
- Write **unit tests**
- Make README more **recruiter-friendly**

Just tell me 🚀
```
