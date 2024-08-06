# Java Backend Project

## Overview

This project is a backend application built using Spring Boot, designed to manage courses, students, and users. It provides various RESTful endpoints for creating, retrieving, updating, and deleting records for each entity.

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Technologies](#technologies)
- [Setup and Installation](#setup-and-installation)
- [API Endpoints](#api-endpoints)
  - [Course Endpoints](#course-endpoints)
  - [Student Endpoints](#student-endpoints)
  - [User Endpoints](#user-endpoints)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## Features

- CRUD operations for Courses, Students, and Users
- User authentication and registration
- Cross-Origin Resource Sharing (CORS) enabled

## Technologies

- Java
- Spring Boot
- Spring Data JPA
- MySQL
- Maven

## Setup and Installation

1. **Clone the repository:**
   ```bash
   git clone https://github.com/shivamtiwari6393/ESD-PROJECT.git
   cd ESD-PROJECT
   ```

2. **Build and run the project:**
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

## API Endpoints

### Course Endpoints

- **Get all courses**
  - `GET /course`
  - Response: List of courses

- **Get course by ID**
  - `GET /course/{courseId}`
  - Response: Course details

- **Create a new course**
  - `POST /course`
  - Request Body: Course object
  - Response: Created course

- **Update a course**
  - `PUT /course/{courseId}`
  - Request Body: Updated course object
  - Response: Update status

- **Delete a course**
  - `DELETE /course/{courseId}`
  - Response: Deletion status

- **Get courses by status**
  - `GET /course/status/{status}`
  - Response: List of courses with the given status

- **Get course by name**
  - `GET /course/find/{courseName}`
  - Response: Course details

### Student Endpoints

- **Get all students**
  - `GET /student`
  - Response: List of students

- **Get student by ID**
  - `GET /student/{studentId}`
  - Response: Student details

- **Create a new student**
  - `POST /student`
  - Request Body: Student object
  - Response: Created student

- **Update a student**
  - `PUT /student/{studentId}`
  - Request Body: Updated student object
  - Response: Update status

- **Delete a student**
  - `DELETE /student/{studentId}`
  - Response: Deletion status

- **Get students by status**
  - `GET /student/status/{status}`
  - Response: List of students with the given status

- **Get student by email**
  - `GET /student/email/{email}`
  - Response: Student details

### User Endpoints

- **Register a new user**
  - `POST /user/registration`
  - Request Body: User object
  - Response: Registration status

- **Login a user**
  - `POST /user/login`
  - Request Body: User object
  - Response: Login status

- **Get all users**
  - `GET /user/all`
  - Response: List of users

- **Delete a user**
  - `DELETE /user/{userId}`
  - Response: Deletion status

- **Update user password**
  - `PUT /user/{userId}/updatepassword`
  - Request Body: New password
  - Response: Update status

- **Update user status**
  - `PUT /user/{userId}/updatestatus/{newStatus}`
  - Response: Updated user

- **Get user by ID**
  - `GET /user/{userId}`
  - Response: User details

- **Get user by username**
  - `GET /user/find/{username}`
  - Response: User details

- **Get users by status**
  - `GET /user/find/status/{status}`
  - Response: List of users with the given status

## Usage

To test the API endpoints, you can use tools like Postman or cURL. For example, to get all courses:
```bash
curl -X GET http://localhost:8080/course
```
