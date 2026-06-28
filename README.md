# Spring User Management

![Java](https://img.shields.io/badge/Java-21-orange)
![Spring MVC](https://img.shields.io/badge/Spring%20MVC-6.2-brightgreen)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-3.1-darkgreen)
![Hibernate](https://img.shields.io/badge/Hibernate-6.6-brown)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-17-blue)
![Maven](https://img.shields.io/badge/Maven-3.9-red)
![Tomcat](https://img.shields.io/badge/Tomcat-10-yellow)

A Spring MVC web application for managing users built with Spring Framework, Hibernate, Thymeleaf, and PostgreSQL.

## Overview

This project is a CRUD (Create, Read, Update, Delete) web application built with the Spring Framework.

The application allows creating, viewing, updating, and deleting users while demonstrating layered architecture, Spring MVC, Hibernate ORM, DTO mapping with MapStruct, validation, and centralized exception handling.

## Features

* Create new users
* View all users
* Update existing users
* Delete users
* Bean Validation
* Email uniqueness validation
* DTO mapping using MapStruct
* Global exception handling
* Responsive user interface
* Layered architecture (Controller → Service → Repository)

## Technologies

### Backend

* Java 21
* Spring Framework 6
    * Spring MVC
    * Spring ORM
    * Spring Transaction Management
* Hibernate ORM
* Jakarta Validation
* MapStruct
* Lombok
* Maven

### Frontend

* Thymeleaf
* HTML5
* CSS3

### Database

* PostgreSQL 17

### Server

* Apache Tomcat 10

## Key Concepts

- MVC Pattern
- Spring MVC
- Layered Architecture
- Repository Pattern
- Service Layer Pattern
- Dependency Injection
- Hibernate ORM
- Bean Validation
- DTO Pattern
- MapStruct
- Transaction Management
- Centralized Exception Handling

## Request Flow

```text
Client Request
       ↓
HiddenHttpMethodFilter
       ↓
DispatcherServlet
       ↓
Controller
       ↓
Service
       ↓
Repository
       ↓
Hibernate
       ↓
PostgreSQL
       ↑
Hibernate
       ↑
Repository
       ↑
Service
       ↑
Controller
       ↓
Thymeleaf
       ↓
HTML Response
 
```

## Project Structure

```text
src/main/java
├── config
├── controller
├── dto
├── entity
├── exception
├── mapper
├── repository
├── service
└── util
```

## Screenshots

### Users List

<img width="1247" height="686" alt="Снимок экрана — 2026-06-27 в 16 15 54" src="https://github.com/user-attachments/assets/d0000e31-ff42-4604-a0fd-f75bb2803916" />


### Create User

<img width="530" height="552" alt="Снимок экрана — 2026-06-27 в 16 20 21" src="https://github.com/user-attachments/assets/1ac820ef-7591-4e4c-94c4-d479b285068d" />


### Edit User

<img width="530" height="552" alt="Снимок экрана — 2026-06-27 в 16 17 27" src="https://github.com/user-attachments/assets/0f3bc5e1-0542-445a-a3d8-8ffc829e22e6" />


### Error Page

<img width="534" height="308" alt="Снимок экрана — 2026-06-27 в 16 20 41" src="https://github.com/user-attachments/assets/5f89a4bf-6539-40dd-91b3-55724d37ede7" />


## Database Schema

```sql
CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    last_name VARCHAR(30) NOT NULL,
    email VARCHAR(50) UNIQUE NOT NULL,
    age INTEGER NOT NULL
);
```

## Getting Started

### Clone Repository

```bash
git clone https://github.com/Wolfanyy/spring-user-management.git
```

### Create Database

```sql
CREATE DATABASE users_db;
```

### Configure Database Connection

The application loads database settings from the `database.properties` file, which is used by the Spring Java configuration to configure the `DataSource`.

Update the database credentials in:

```text
src/main/resources/database.properties
```

Example:

```properties
db.url=jdbc:postgresql://localhost:5433/users_db
db.username=postgres
db.password=your_password
```

### Build Project

```bash
mvn clean package
```

### Deploy

Deploy the generated WAR file to Apache Tomcat.

## Validation Rules

### Name

* Required
* Length between 3 and 30 characters
* Letters, spaces, and hyphens only

### Last Name

* Required
* Length between 3 and 30 characters
* Letters, spaces, and hyphens only

### Email

* Required
* Valid email format
* Must be unique

### Age

* Required
* Range from 1 to 100

## Error Handling

The application uses centralized exception handling with @ControllerAdvice.

Handled exceptions include:

* ValidationException
* UserNotFoundException
* DuplicateEmailException
* Unexpected server errors

## Author

**Anna Gribanova**

GitHub: https://github.com/Wolfanyy
