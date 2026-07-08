# Full Stack Task Tracker

A full-stack Task Tracker application built with **Spring Boot**, **React**, and **MySQL**.

---

# Features

- Create, View and Delete Projects
- Create, View and Delete Tasks
- Project → Task One-to-Many Relationship
- Pagination
- Filter Tasks by Status
- Filter Tasks by Priority
- Sort Tasks by Due Date
- Server-side Validation
- Swagger API Documentation
- Responsive React Frontend

---

# Tech Stack

## Backend

- Java 21
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL
- Maven

## Frontend

- React
- React Router
- Axios
- Vite

## Database

- MySQL

---

# Database Schema

Project

- id
- name

Task

- id
- title
- description
- status
- priority
- dueDate
- createdAt
- updatedAt
- project_id (Foreign Key)

Relationship

One Project → Many Tasks

---

# API Endpoints

## Projects

| Method | Endpoint |
|---------|----------|
| GET | /projects |
| GET | /projects/{id} |
| POST | /projects |
| DELETE | /projects/{id} |

## Tasks

| Method | Endpoint |
|---------|----------|
| GET | /tasks |
| GET | /tasks/{id} |
| POST | /tasks |
| PUT | /tasks/{id} |
| DELETE | /tasks/{id} |

Example Filters

```
GET /tasks?status=TODO

GET /tasks?priority=HIGH

GET /tasks?page=0&size=10

GET /tasks?sortBy=dueDate
```

---

# Backend Setup

## 1 Create Database

```
CREATE DATABASE tasktracker;
```

## 2 Configure

Update

```
src/main/resources/application.properties
```

with your MySQL username and password.

## 3 Run

```
mvn spring-boot:run
```

Backend

```
http://localhost:8080
```

Swagger

```
http://localhost:8080/swagger-ui/index.html
```

---

# Frontend Setup

```
cd frontend

npm install

npm run dev
```

Frontend

```
http://localhost:5173
```

---

# Project Structure

```
backend
 ├── controller
 ├── service
 ├── repository
 ├── entity
 ├── dto
 ├── exception

frontend
 ├── components
 ├── pages
 ├── services
 ├── App.jsx
```

---

# Design Decisions

- RESTful API architecture
- Layered backend architecture
- DTO used for Task requests
- One-to-Many Project–Task relationship
- Server-side validation using Jakarta Validation
- Pagination, filtering and sorting implemented in Spring Data JPA
- React SPA communicating through Axios

---

# Future Improvements

- JWT Authentication
- Edit Task UI
- Mark Task as Complete
- Dashboard Charts
- Docker Compose
- GitHub Actions CI/CD
- Unit & Integration Tests

---

# AI Usage

This project was developed by me.

AI assistance (ChatGPT) was used for:

- debugging issues
- React component improvements
- API design guidance
- README formatting

All generated suggestions were reviewed, modified, tested, and integrated manually.

---

# Author

**Nirisha Karuppaiah**
