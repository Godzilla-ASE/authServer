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

## Continuous Integration
We utilize SonarQube deployment to achieve continuous integration in our project, ensuring code quality and reliability. SonarQube is a powerful static code analysis tool that helps us identify potential issues, defects, and vulnerabilities in our code, while providing comprehensive code quality metrics.

In our continuous integration workflow, whenever code is committed to the version control repository, SonarQube automatically runs static code analysis and generates detailed reports on code quality and health. This allows us to identify and address potential issues early, ensuring code maintainability and scalability.

By leveraging SonarQube, we gain the following benefits:

- Code Quality Analysis: SonarQube provides comprehensive code quality metrics, including code complexity, code duplication, potential defects, and vulnerabilities.
- Instant Feedback: After each code commit, we can immediately review SonarQube reports to understand any issues in the code and address them promptly.
- Custom Rules: We can configure custom code rules based on project-specific requirements and apply them during static code analysis.
- Continuous Improvement: By periodically reviewing SonarQube reports, we can track the evolution of code quality and continuously improve the quality of our project's code.

## Author

ASE Godzilla group : Han Yang

