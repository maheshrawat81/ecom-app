# User Management Service

A Spring Boot application written in Kotlin for managing user accounts, using CouchDB as the database.

## Features

- **User Registration** -: Create new user accounts.
- **User Authentication** -: Login and logout functionality.
- **CRUD Operations** -: Full CRUD functionality for managing users.
- **JSON-based Document Storage** -: Powered by CouchDB.

## Technologies Used

- **Spring Boot** - Backend framework
- **Kotlin** - Programming language
- **Spring Security & JWT** - Authentication
- **Spring Data CouchDB** - Database interaction
- **CouchDB** - NoSQL database
- **Maven** - Build tool

## Getting Started

### 1. Clone the Repository
To get started, clone the repository and navigate to the project directory:
```bash
git clone https://github.com/maheshrawat81/ecom-app.git
cd user-management-service
```



### 2. Set Up CouchDB
Install CouchDB from CouchDB Downloads.
Ensure CouchDB is running locally on the default port (5984).
Create a database named ecom in CouchDB.


### 3.Build and Run the Application 
Build the project:

```bash
mvn clean install
mvn spring-boot:run
```

### 4. API Endpoints

| Method | Endpoint              | Description                                                              |
|--------|------------------------|--------------------------------------------------------------------------|
| `POST` | `/register`            | Register a new user. Expects a JSON payload of user details.             |
| `POST` | `/login`               | Authenticate a user and return a JWT token. Expects JSON with username and password. |
| `GET`  | `/{username}`          | Retrieve user details by username.                                       |
| `GET`  | `/test`                | Test an external HTTP call using a provided token as a request parameter.|
| `GET`  | `/authenticateToken`   | Extract and authenticate the JWT token from the request header and return the username. |


## Resources
- **Kotlin**: [Kotlin Tutorial - Javatpoint](https://www.javatpoint.com/kotlin-tutorial)
- **CouchDB**: [CouchDB Tutorial](https://guide.couchdb.org/draft/tour.html)