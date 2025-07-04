# Library System

A comprehensive library management system built with microservices architecture using Spring Boot, Spring Cloud, and Docker.

## Demo Videos

- **Part 1 - Infrastructure Setup**: [https://www.youtube.com/watch?v=Gqf7qObCQO0](https://www.youtube.com/watch?v=Gqf7qObCQO0)
- **Part 2 - Applications**: [https://www.youtube.com/watch?v=EsyfQkC1OQ0](https://www.youtube.com/watch?v=EsyfQkC1OQ0)

## Architecture

This project implements a library management system using Spring Cloud microservices architecture with the following components:

## Architecture Components

1. **Discovery Server (Eureka)** - Service registry and discovery
2. **Configuration Server** - Centralized configuration management
3. **API Gateway** - Single entry point for all client requests
4. **Admin Service** - Authentication and authorization management
5. **Catalog Service** - MARC record and book catalog management
6. **Patron Service** - Patron/User management
7. **Circulation Service** - Book checkout/return management
8. **Notification Service** - Email and notification management
9. **Integration Service** - External system integrations
10. **Reporting Service** - Report generation and management
11. **Library Service** - Core business logic for book management

## Prerequisites

- Java 17 or higher
- Maven 3.6 or higher
- Docker and Docker Compose
- Git (for configuration server)

## Project Structure

```
library-system/
├── discovery-server/          # Eureka Discovery Server (Port: 18761)
├── config-server/             # Spring Cloud Config Server (Port: 8888)
├── api-gateway/               # Spring Cloud Gateway (Port: 8080)
├── admin-service/             # Admin Authentication Service (Port: 8088)
├── catalog-service/           # Catalog Management Service (Port: 8082)
├── patron-service/            # Patron Management Service (Port: 8083)
├── circulation-service/       # Circulation Management Service (Port: 8084)
├── notification-service/      # Notification Service (Port: 8085)
├── integration-service/       # Integration Service (Port: 8086)
├── reporting-service/         # Reporting Service (Port: 8087)
├── library-service/           # Library Management Service (Port: 8081)
├── docker-compose.yml         # Docker Compose configuration
└── pom.xml                    # Parent POM
```

## Getting Started

### 1. Using Docker Compose (Recommended)

The easiest way to run the entire system is using Docker Compose:

```bash
# Start all services
docker-compose up -d

# Check service status
docker-compose ps

# View logs
docker-compose logs -f

# Stop all services
docker-compose down
```

### 2. Manual Build and Run

#### Build the Project

```bash
mvn clean install
```

#### Start the Services

Start the services in the following order:

##### Discovery Server
```bash
cd discovery-server
mvn spring-boot:run
```
Access Eureka Dashboard: http://localhost:18761

##### Configuration Server
```bash
cd config-server
mvn spring-boot:run
```

##### Admin Service
```bash
cd admin-service
mvn spring-boot:run
```

##### Catalog Service
```bash
cd catalog-service
mvn spring-boot:run
```

##### Patron Service
```bash
cd patron-service
mvn spring-boot:run
```

##### Circulation Service
```bash
cd circulation-service
mvn spring-boot:run
```

##### Notification Service
```bash
cd notification-service
mvn spring-boot:run
```

##### Integration Service
```bash
cd integration-service
mvn spring-boot:run
```

##### Reporting Service
```bash
cd reporting-service
mvn spring-boot:run
```

##### API Gateway
```bash
cd api-gateway
mvn spring-boot:run
```

### 3. Access the Application

- **API Gateway**: http://localhost:8080
- **Admin Panel**: http://localhost:8088/admin/login
- **Eureka Dashboard**: http://localhost:18761
- **Config Server**: http://localhost:8888
- **Catalog Service**: http://localhost:8082
- **Patron Service**: http://localhost:8083
- **Circulation Service**: http://localhost:8084
- **Notification Service**: http://localhost:8085
- **Integration Service**: http://localhost:8086
- **Reporting Service**: http://localhost:8087

## Admin Authentication

The system includes a comprehensive admin authentication system with the following features:

### Default Admin Accounts

The system automatically creates two default admin accounts on first startup:

1. **Super Administrator**
   - Username: `superadmin`
   - Password: `superadmin123`
   - Role: SUPER_ADMIN
   - Permissions: Full system access

2. **Administrator**
   - Username: `admin`
   - Password: `admin123`
   - Role: ADMIN
   - Permissions: Standard admin access

### Admin Roles

- **SUPER_ADMIN**: Full system access, can manage other admins
- **ADMIN**: Standard administrative access
- **LIBRARIAN**: Library-specific operations
- **ASSISTANT**: Basic operations and viewing

### Authentication Features

- JWT-based authentication
- Role-based access control
- Password encryption with BCrypt
- Session management
- Account status management (Active, Inactive, Suspended, Locked)

## API Endpoints

### Admin Service (Port 8088)

#### Authentication Endpoints
- **Login**: `POST /admin/auth/login`
- **Register**: `POST /admin/auth/register`
- **Validate Token**: `POST /admin/auth/validate`
- **Logout**: `POST /admin/auth/logout`

#### Admin Management Endpoints
- **Get all admins**: `GET /admin/api/admins`
- **Get admin by ID**: `GET /admin/api/admins/{id}`
- **Create admin**: `POST /admin/api/admins`
- **Update admin**: `PUT /admin/api/admins/{id}`
- **Delete admin**: `DELETE /admin/api/admins/{id}`
- **Get admins by role**: `GET /admin/api/admins/role/{role}`
- **Get admins by status**: `GET /admin/api/admins/status/{status}`
- **Search admins**: `GET /admin/api/admins/search?query={query}`
- **Update permissions**: `PUT /admin/api/admins/{id}/permissions`
- **Change password**: `PUT /admin/api/admins/{id}/password`
- **Lock admin**: `PUT /admin/api/admins/{id}/lock`
- **Unlock admin**: `PUT /admin/api/admins/{id}/unlock`

### Catalog Service (Port 8082)

- **Get all MARC records**: `GET /catalog/api/records`
- **Get MARC record by ID**: `GET /catalog/api/records/{id}`
- **Create MARC record**: `POST /catalog/api/records`
- **Update MARC record**: `PUT /catalog/api/records/{id}`
- **Delete MARC record**: `DELETE /catalog/api/records/{id}`
- **Search MARC records**: `GET /catalog/api/search?query={query}`
- **Upload MARC file**: `POST /admin/upload-marc`
- **Search books**: `GET /search?title={title}&author={author}&isbn={isbn}`

### Patron Service (Port 8083)

- **Get all patrons**: `GET /patrons/api/patrons`
- **Get patron by ID**: `GET /patrons/api/patrons/{id}`
- **Create patron**: `POST /patrons/api/patrons`
- **Update patron**: `PUT /patrons/api/patrons/{id}`
- **Delete patron**: `DELETE /patrons/api/patrons/{id}`
- **Search patrons**: `GET /patrons/api/search?query={query}`

### Circulation Service (Port 8084)

- **Get all circulation records**: `GET /circulation/api/records`
- **Get circulation record by ID**: `GET /circulation/api/records/{id}`
- **Create circulation record**: `POST /circulation/api/records`
- **Update circulation record**: `PUT /circulation/api/records/{id}`
- **Delete circulation record**: `DELETE /circulation/api/records/{id}`
- **Checkout book**: `GET /circulation/api/checkout?bookId={bookId}&patronId={patronId}`
- **Return book**: `POST /circulation/api/return/{id}`
- **Get overdue records**: `GET /circulation/api/overdue`

### Notification Service (Port 8085)

- **Get all notifications**: `GET /notifications/api/notifications`
- **Get notification by ID**: `GET /notifications/api/notifications/{id}`
- **Create notification**: `POST /notifications/api/notifications`
- **Update notification**: `PUT /notifications/api/notifications/{id}`
- **Delete notification**: `DELETE /notifications/api/notifications/{id}`
- **Send notification**: `POST /notifications/api/notifications/{id}/send`
- **Get notifications by type**: `GET /notifications/api/notifications/type/{type}`
- **Get notifications by status**: `GET /notifications/api/notifications/status/{status}`

### Integration Service (Port 8086)

- **Get all integration configs**: `GET /integration/api/configs`
- **Get integration config by ID**: `GET /integration/api/configs/{id}`
- **Create integration config**: `POST /integration/api/configs`
- **Update integration config**: `PUT /integration/api/configs/{id}`
- **Delete integration config**: `DELETE /integration/api/configs/{id}`
- **Test connection**: `POST /integration/api/configs/{id}/test`
- **Get configs by type**: `GET /integration/api/configs/type/{type}`
- **Get configs by status**: `GET /integration/api/configs/status/{status}`

### Reporting Service (Port 8087)

- **Get all reports**: `GET /reports/api/reports`
- **Get report by ID**: `GET /reports/api/reports/{id}`
- **Create report**: `POST /reports/api/reports`
- **Update report**: `PUT /reports/api/reports/{id}`
- **Delete report**: `DELETE /reports/api/reports/{id}`
- **Generate report**: `POST /reports/api/reports/{id}/generate`
- **Get reports by type**: `GET /reports/api/reports/type/{type}`
- **Get reports by status**: `GET /reports/api/reports/status/{status}`
- **Get scheduled reports**: `GET /reports/api/reports/scheduled`

## Infrastructure Services

- **PostgreSQL Database**: Port 5432
- **Kafka**: Port 9092
- **Zookeeper**: Port 2181
- **Elasticsearch**: Port 9200
- **Kibana**: Port 5601
- **Logstash**: Port 5001

## Configuration

### Discovery Server Configuration
- Port: 18761
- Self-preservation mode: Disabled for development
- Wait time: 0ms for faster startup

### Configuration Server
- Port: 8888
- Git repository: Configure your own repository
- Search paths: `config`

### API Gateway Configuration
- Port: 8080
- Routes configured for all services
- Service discovery enabled

### Admin Service Configuration
- Port: 8088
- JWT secret: librarySystemJwtSecretKey2024
- JWT expiration: 24 hours
- Default admin accounts created on startup

### Database Configuration
- PostgreSQL with connection pooling
- JPA with Hibernate
- Sample data loaded on startup

## Features

- **Service Discovery**: Automatic service registration and discovery
- **Load Balancing**: Client-side load balancing with Ribbon
- **Centralized Configuration**: Externalized configuration management
- **API Gateway**: Single entry point with routing and filtering
- **Authentication & Authorization**: JWT-based security with role-based access control
- **Health Checks**: Actuator endpoints for monitoring
- **Database**: PostgreSQL with connection pooling
- **RESTful API**: Complete CRUD operations for all entities
- **Validation**: Input validation using Bean Validation
- **Logging**: Structured logging with Logback
- **Monitoring**: ELK stack integration
- **Message Queue**: Kafka integration for async processing

## Monitoring

- **Eureka Dashboard**: View registered services
- **Actuator Endpoints**: Health checks and metrics
- **Kibana**: Log visualization and analysis
- **Elasticsearch**: Log storage and search

## Development

### Adding New Services

1. Create a new module in the parent project
2. Add the module to the parent `pom.xml`
3. Include Eureka Client dependency
4. Configure the service in the API Gateway routes
5. Add Docker configuration

### Configuration Management

1. Create a Git repository for configuration
2. Update the config server's Git URI
3. Place configuration files in the repository
4. Services will automatically fetch configurations

## Testing

Run the test script to verify all services are working:

```bash
./run-tests.sh
```

## Troubleshooting

1. **Service not registering**: Check Eureka server is running and accessible
2. **Configuration not loading**: Verify Git repository URI and connectivity
3. **Gateway routing issues**: Check service names and route configurations
4. **Database issues**: Verify PostgreSQL connection and credentials
5. **Docker issues**: Check Docker and Docker Compose installation
6. **Authentication issues**: Verify JWT configuration and admin accounts

## Next Steps

- Add authentication and authorization
- Implement circuit breakers with Resilience4j
- Add distributed tracing with Sleuth and Zipkin
- Implement logging aggregation
- Add monitoring with Prometheus and Grafana
- Add CI/CD pipelines
- Implement comprehensive testing

## Admin Panel Login

You can access the admin panel at [http://localhost:8088/admin/login](http://localhost:8088/admin/login)

**Default Admin Accounts:**

- Username: `superadmin`  Password: `superadmin123` (Super Admin)
- Username: `admin`       Password: `admin123` (Admin)

