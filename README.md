# Spring Boot Project

## Project Overview
This project is built using Spring Boot version 3.0.5 and Java 19. It employs an H2 in-memory database for caching and initial data setup. The primary functionality of this application is to manage users and groups via RESTful APIs.

## Prerequisites
- Java 19
- Postman (optional, for API testing)

## Database Setup
The project uses an H2 in-memory database. You don't need to do any external database setup. The database schema and sample data are automatically initialized when the application starts.

## Database Configuration
The database configuration is specified in the application.yml file

## Adding Sample Data
The data.sql file in the src/main/resources directory contains sample data that will be automatically loaded into the H2 database upon application startup.

## Running the Project
1. Build the project using: mvn clean install
2. Use Postman or any other REST client to interact with the APIs listed below.

## User APIs

### Create User
- **POST** `/api/users`

### Get User by ID
- **GET** `/api/users/{id}`

### Get All Users
- **GET** `/api/users`

### Update User
- **PUT** `/api/users/{id}`

### Delete User
- **DELETE** `/api/users/{id}`

### Add User to Group
- **POST** `/api/users/{userId}/groups/{groupId}`

### Remove User from Group
- **DELETE** `/api/users/{userId}/groups/{groupId}`

### Get User Groups
- **GET** `/api/users/{userId}/groups`

## Group APIs

### Create Group
- **POST** `/api/groups`

### Get Group by ID
- **GET** `/api/groups/{id}`

### Get All Groups
- **GET** `/api/groups`

### Update Group
- **PUT** `/api/groups/{id}`

### Delete Group
- **DELETE** `/api/groups/{id}`

### Get Group Users
- **GET** `/api/groups/{groupId}/users`

## Testing
JUnit 5 and Mockito are used for running test cases. Run the provided test cases to ensure the proper functionality of the APIs.

## Notes
- Ensure the SQL Server password is securely stored and not exposed in the `application.properties` file in production environments.
- Review and customize the SQL scripts and APIs as needed to fit your project requirements.

Feel free to reach out if you have any questions or need further assistance!
