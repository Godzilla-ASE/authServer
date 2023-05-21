# Authentication Service

Authentication Service is a backend service that handles user authentication and authorization:
- Generate token for userService
- Response with the token of the requested user
- Delete token when user logout 
## Prerequisites

- Java Development Kit (JDK) 8 or higher
- Apache Maven

## Installation

1. Clone the repository.
2. Navigate to the project directory.
3. Build the project using Maven by running the following command:

```bash
mvn clean install
```


## Configuration

1. Open the `application.properties` file in the `src/main/resources` directory.
2. Provide the necessary configuration values, such as database connection details.
   Make sure to replace the values with your actual database connection details.

The configuration properties are used for the following purposes:

- spring.datasource.url: Specifies the URL for the MySQL database connection.
- spring.datasource.username and spring.datasource.password: Provide the username and password for the database connection.
- spring.jpa.properties.hibernate.dialect: Sets the Hibernate dialect for MySQL.
- spring.jpa.hibernate.ddl-auto: Determines how Hibernate handles the database schema updates. The update value indicates that Hibernate will automatically update the schema based on the entity mappings.
- server.port: Specifies the port on which the application will run locally.
- Please ensure that you have a MySQL database running with the specified connection details before running the application.

## Usage

1. Start the Authentication Service by running the following command:

```bash
./mvnw spring-boot:run
```


2. The service will be accessible at `http://localhost:8081`.

## API Endpoints

The Authentication Service provides the following API endpoints:

- `POST /auth/{id}`: Create a new token for a user.
- `GET /auth/{id}`: Get a exist token for a user.
- `DELETE /auth/{id}`: Delete a exist token for a user.

## Testing

- Testing includes tests for three interfaces
- Test each of them for the functionalities and integration with the database

## Author

ASE Godzilla group : Han Yang

