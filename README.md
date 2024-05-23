# Spring Boot Project

## Project Overview
This is a Spring Boot project version 3.0.5 with Java 19. It utilizes SQL Server as its database management system. The project includes RESTful APIs for managing users and groups.

## Prerequisites
- Java 19
- SQL Server
- Postman (optional, for API testing)

## Setting Up SQL Server
1. Ensure SQL Server is installed and running.
2. Update the SQL Server password in the `application.properties` file to match your SQL Server credentials.

## Database Setup
1. Create a database named `user_group_assignment`.
2. Configure the `application.properties` file to connect to the `user_group_assignment` database.
3. Set `ddl-auto` property to `update` in `application.properties`. This will create tables automatically.

## Adding Sample Data
Use the provided SQL file to add sample data to the `user_group_assignment` database.

## Running the Project
1. Build and run the project.
2. Use Postman or any other REST client to make requests to the following APIs:

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
