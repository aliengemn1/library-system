# Library Management System - Microservices Architecture

This project implements a library management system using Spring Cloud microservices architecture with the following components:

## Architecture Components

1. **Discovery Server (Eureka)** - Service registry and discovery
2. **Configuration Server** - Centralized configuration management
3. **API Gateway** - Single entry point for all client requests
4. **Library Service** - Core business logic for book management

## Prerequisites

- Java 17 or higher
- Maven 3.6 or higher
- Git (for configuration server)

## Project Structure

```
library-system/
├── discovery-server/          # Eureka Discovery Server (Port: 8761)
├── config-server/             # Spring Cloud Config Server (Port: 8888)
├── api-gateway/               # Spring Cloud Gateway (Port: 8080)
├── library-service/           # Library Management Service (Port: 8081)
└── pom.xml                    # Parent POM
```

## Getting Started

### 1. Build the Project

```bash
mvn clean install
```

### 2. Start the Services

Start the services in the following order:

#### Discovery Server
```bash
cd discovery-server
mvn spring-boot:run
```
Access Eureka Dashboard: http://localhost:8761

#### Configuration Server
```bash
cd config-server
mvn spring-boot:run
```
**Note**: Update the Git repository URI in `config-server/src/main/resources/application.yml` to point to your configuration repository.

#### Library Service
```bash
cd library-service
mvn spring-boot:run
```

#### API Gateway
```bash
cd api-gateway
mvn spring-boot:run
```

### 3. Access the Application

- **API Gateway**: http://localhost:8080
- **Eureka Dashboard**: http://localhost:8761
- **H2 Database Console**: http://localhost:8081/h2-console
  - JDBC URL: `jdbc:h2:mem:librarydb`
  - Username: `sa`
  - Password: `password`

## API Endpoints

All requests should go through the API Gateway at `http://localhost:8080`

### Book Management

- **Get all books**: `GET /api/books`
- **Get book by ID**: `GET /api/books/{id}`
- **Get book by ISBN**: `GET /api/books/isbn/{isbn}`
- **Get books by author**: `GET /api/books/author/{author}`
- **Get books by title**: `GET /api/books/title/{title}`
- **Get available books**: `GET /api/books/available`
- **Get books by year**: `GET /api/books/year/{year}`
- **Create book**: `POST /api/books`
- **Update book**: `PUT /api/books/{id}`
- **Delete book**: `DELETE /api/books/{id}`
- **Borrow book**: `POST /api/books/{id}/borrow`
- **Return book**: `POST /api/books/{id}/return`

### Example API Usage

#### Create a new book
```bash
curl -X POST http://localhost:8080/api/books \
  -H "Content-Type: application/json" \
  -d '{
    "title": "The Catcher in the Rye",
    "author": "J.D. Salinger",
    "isbn": "978-0316769488",
    "description": "A classic novel about teenage alienation",
    "publicationYear": 1951
  }'
```

#### Get all books
```bash
curl http://localhost:8080/api/books
```

#### Borrow a book
```bash
curl -X POST http://localhost:8080/api/books/1/borrow
```

## Configuration

### Discovery Server Configuration
- Port: 8761
- Self-preservation mode: Disabled for development
- Wait time: 0ms for faster startup

### Configuration Server
- Port: 8888
- Git repository: Configure your own repository
- Search paths: `config`

### API Gateway Configuration
- Port: 8080
- Routes configured for `/api/books/**` and `/api/health/**`
- Service discovery enabled

### Library Service Configuration
- Port: 8081
- H2 in-memory database
- JPA with Hibernate
- Sample data loaded on startup

## Features

- **Service Discovery**: Automatic service registration and discovery
- **Load Balancing**: Client-side load balancing with Ribbon
- **Centralized Configuration**: Externalized configuration management
- **API Gateway**: Single entry point with routing and filtering
- **Health Checks**: Actuator endpoints for monitoring
- **Database**: H2 in-memory database with sample data
- **RESTful API**: Complete CRUD operations for books
- **Validation**: Input validation using Bean Validation

## Monitoring

- **Eureka Dashboard**: View registered services
- **Actuator Endpoints**: Health checks and metrics
- **H2 Console**: Database inspection

## Development

### Adding New Services

1. Create a new module in the parent project
2. Add the module to the parent `pom.xml`
3. Include Eureka Client dependency
4. Configure the service in the API Gateway routes

### Configuration Management

1. Create a Git repository for configuration
2. Update the config server's Git URI
3. Place configuration files in the repository
4. Services will automatically fetch configurations

## Troubleshooting

1. **Service not registering**: Check Eureka server is running and accessible
2. **Configuration not loading**: Verify Git repository URI and connectivity
3. **Gateway routing issues**: Check service names and route configurations
4. **Database issues**: Verify H2 console access and connection settings

## Next Steps

- Add authentication and authorization
- Implement circuit breakers with Hystrix
- Add distributed tracing with Sleuth
- Implement logging aggregation
- Add monitoring with Prometheus and Grafana
- Containerize with Docker
- Deploy to Kubernetes 