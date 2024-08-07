# Spring Boot Application with H2 Database

This project is a Spring Boot application that utilizes an H2 in-memory database. It is packaged into a Docker container for easy deployment.

## Prerequisites

- **Docker:** Ensure Docker is installed on your machine. You can download it from [Docker's official website](https://www.docker.com/get-started).
- **Maven or Gradle:** These build tools are used for building the Spring Boot application. Ensure you have one of them installed.

## Project Setup

### 1. Clone the Repository

#### Clone the repository containing the project: ####

git clone https://github.com/ankme5/Student-Management-System.git 

cd Student-Management-System/backend

### 2. Build the Spring Boot Application 

#### Build the Spring Boot JAR file: ####

./mvnw clean package

### 3. Build the Docker Image

#### Create the Docker image from the Dockerfile provided: ####

docker build -t your-image-name .

### 4. Run the Docker Container

#### Run the Docker container from the image: ####

docker run -p 8080:8080 your-image-name

## API Endpoint: The application will be accessible at http://localhost:8080.

## Steps to use Application ##

1. Register
2. Login
3. Fetch Token
4. New Students 
5. New Subjects
6. Enroll Subject
7. Fetch All Subjects
8. Fetch All Students

All Apis are accessable by ADMIN 

APIS accessable by STUDENT  
Enroll Subject
Fetch All Subjects
Fetch All Students

##  POSTMAN Collection For Testing ##
https://elements.getpostman.com/redirect?entityId=34216471-ab1b0daa-3ca9-444a-9339-afef68ed0fc3&entityType=collection



