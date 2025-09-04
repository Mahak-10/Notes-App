Notes App (Microservices Architecture)

Overview

The Notes App is a microservices-based application built with Spring Boot, designed to manage personal notes efficiently. The project consists of two independent microservices:
1. User Service – Handles user registration, management, and validation.
2. Notes Service – Manages note creation, reading, updating, deletion (CRUD), and sharing functionality.
Both services communicate via REST APIs, and the architecture supports scalability, maintainability, and easy deployment.
Deployment: Can be deployed on cloud platforms like Render, AWS, or Heroku

Features

User Service
⦁	Register and manage users
⦁	Validate users for note operation
⦁	Connects with PostgreSQL for persistent storage


Notes Service
⦁	Create, read, update, and delete notes
⦁	Generate shareable note links
⦁	Validate users via User Service before note creation
⦁	In-memory or persistent database support (H2 or PostgreSQL)

Tech Stack

⦁	Backend: Java, Spring Boot, Spring Data JPA
⦁	Databases: H2 (in-memory) / PostgreSQL / MySQL
⦁	Containerization: Docker
⦁	API Testing: Postman
⦁	Deployment: Can be deployed on cloud platforms like Render, AWS, or Heroku


Structure

notes-app/
  user-service/        # User microservice
    src/
    pom.xml
    Dockerfile

  notes-service/       # Notes microservice
    src/
    pom.xml
    Dockerfile


