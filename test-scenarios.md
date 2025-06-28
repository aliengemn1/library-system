# Library Management System - Test Scenarios

## 1. Service Discovery Tests

### 1.1 Eureka Discovery Server
- **Test**: Verify Eureka server starts and is accessible
- **URL**: http://localhost:8761
- **Expected**: Eureka dashboard shows no instances initially
- **Status**: ✅ PASS

### 1.2 Service Registration
- **Test**: Verify all services register with Eureka
- **Services**: Config Server, API Gateway, Catalog, Patron, Circulation, Notification, Integration, Reporting
- **Expected**: All services appear in Eureka dashboard
- **Status**: ⏳ PENDING

## 2. Configuration Server Tests

### 2.1 Config Server Health
- **Test**: Verify config server is healthy
- **URL**: http://localhost:8888/actuator/health
- **Expected**: Status UP
- **Status**: ⏳ PENDING

### 2.2 Configuration Distribution
- **Test**: Verify services can fetch configuration
- **Method**: Check service logs for successful config fetch
- **Expected**: No configuration errors in service logs
- **Status**: ⏳ PENDING

## 3. API Gateway Tests

### 3.1 Gateway Health
- **Test**: Verify API Gateway is healthy
- **URL**: http://localhost:8080/actuator/health
- **Expected**: Status UP
- **Status**: ⏳ PENDING

### 3.2 Route Configuration
- **Test**: Verify all service routes are configured
- **Routes to test**:
  - `/api/catalog/**` → Catalog Service
  - `/api/patrons/**` → Patron Service
  - `/api/circulation/**` → Circulation Service
  - `/api/notifications/**` → Notification Service
  - `/api/integration/**` → Integration Service
  - `/api/reports/**` → Reporting Service
- **Status**: ⏳ PENDING

## 4. Database Tests

### 4.1 PostgreSQL Connection
- **Test**: Verify PostgreSQL is accessible
- **Command**: `docker exec library-postgres pg_isready -U library_user -d library_db`
- **Expected**: PostgreSQL is ready to accept connections
- **Status**: ⏳ PENDING

### 4.2 Database Schema
- **Test**: Verify all tables are created
- **Tables**: marc_records, marc_fields, patrons, books, etc.
- **Expected**: All required tables exist
- **Status**: ⏳ PENDING

## 5. Catalog Service Tests

### 5.1 Service Health
- **Test**: Verify catalog service is healthy
- **URL**: http://localhost:8082/actuator/health
- **Expected**: Status UP
- **Status**: ⏳ PENDING

### 5.2 MARC Record CRUD Operations
- **Test**: Create MARC record
- **Method**: POST /api/catalog/records
- **Payload**: 
```json
{
  "controlNumber": "TEST001",
  "leader": "00000nam a2200000   4500",
  "fields": [
    {
      "tag": "245",
      "indicator1": "1",
      "indicator2": "0",
      "subfields": [
        {"code": "a", "value": "Test Book Title"}
      ]
    }
  ]
}
```
- **Expected**: 201 Created with record ID
- **Status**: ⏳ PENDING

### 5.3 MARC Record Retrieval
- **Test**: Retrieve MARC record by ID
- **Method**: GET /api/catalog/records/{id}
- **Expected**: 200 OK with record data
- **Status**: ⏳ PENDING

### 5.4 MARC Record Search
- **Test**: Search MARC records
- **Method**: GET /api/catalog/records/search?q=test
- **Expected**: 200 OK with search results
- **Status**: ⏳ PENDING

## 6. Patron Service Tests

### 6.1 Service Health
- **Test**: Verify patron service is healthy
- **URL**: http://localhost:8083/actuator/health
- **Expected**: Status UP
- **Status**: ⏳ PENDING

### 6.2 Patron CRUD Operations
- **Test**: Create patron
- **Method**: POST /api/patrons
- **Payload**:
```json
{
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com",
  "phoneNumber": "+1234567890",
  "address": "123 Main St",
  "membershipType": "REGULAR",
  "status": "ACTIVE"
}
```
- **Expected**: 201 Created with patron ID
- **Status**: ⏳ PENDING

### 6.3 Patron Retrieval
- **Test**: Retrieve patron by ID
- **Method**: GET /api/patrons/{id}
- **Expected**: 200 OK with patron data
- **Status**: ⏳ PENDING

## 7. Circulation Service Tests

### 7.1 Service Health
- **Test**: Verify circulation service is healthy
- **URL**: http://localhost:8084/actuator/health
- **Expected**: Status UP
- **Status**: ⏳ PENDING

### 7.2 Checkout Process
- **Test**: Check out a book to a patron
- **Method**: POST /api/circulation/checkout
- **Payload**:
```json
{
  "patronId": 1,
  "bookId": 1,
  "dueDate": "2024-02-01"
}
```
- **Expected**: 201 Created with checkout record
- **Status**: ⏳ PENDING

## 8. Integration Tests

### 8.1 End-to-End Workflow
- **Test**: Complete library workflow
- **Steps**:
  1. Create patron
  2. Create MARC record
  3. Check out book
  4. Verify checkout record
- **Expected**: All operations succeed
- **Status**: ⏳ PENDING

### 8.2 Service Communication
- **Test**: Verify services can communicate via API Gateway
- **Method**: Test all routes through gateway
- **Expected**: All requests routed correctly
- **Status**: ⏳ PENDING

## 9. Performance Tests

### 9.1 Load Testing
- **Test**: Handle concurrent requests
- **Tool**: Apache Bench or similar
- **Command**: `ab -n 1000 -c 10 http://localhost:8080/api/catalog/records`
- **Expected**: All requests processed successfully
- **Status**: ⏳ PENDING

### 9.2 Response Time
- **Test**: Measure response times
- **Expected**: < 500ms for most operations
- **Status**: ⏳ PENDING

## 10. Error Handling Tests

### 10.1 Invalid Requests
- **Test**: Send invalid JSON
- **Expected**: 400 Bad Request
- **Status**: ⏳ PENDING

### 10.2 Resource Not Found
- **Test**: Request non-existent resource
- **Expected**: 404 Not Found
- **Status**: ⏳ PENDING

### 10.3 Validation Errors
- **Test**: Send data with validation errors
- **Expected**: 400 Bad Request with error details
- **Status**: ⏳ PENDING

## 11. Docker Container Tests

### 11.1 Container Health
- **Test**: Verify all containers are running
- **Command**: `docker ps`
- **Expected**: All containers in "Up" state
- **Status**: ⏳ PENDING

### 11.2 Container Logs
- **Test**: Check for errors in container logs
- **Command**: `docker logs <container-name>`
- **Expected**: No ERROR level logs
- **Status**: ⏳ PENDING

### 11.3 Resource Usage
- **Test**: Monitor container resource usage
- **Command**: `docker stats`
- **Expected**: Reasonable CPU and memory usage
- **Status**: ⏳ PENDING

## 12. Security Tests

### 12.1 Port Exposure
- **Test**: Verify only necessary ports are exposed
- **Expected**: Only defined ports accessible
- **Status**: ⏳ PENDING

### 12.2 Database Security
- **Test**: Verify database credentials are secure
- **Expected**: No hardcoded credentials in logs
- **Status**: ⏳ PENDING

## Test Execution Commands

### Start the System
```bash
# Build and start all services
docker-compose up --build -d

# Check service status
docker-compose ps

# View logs
docker-compose logs -f
```

### Run Health Checks
```bash
# Check all service health endpoints
curl http://localhost:8761/actuator/health  # Discovery
curl http://localhost:8888/actuator/health  # Config
curl http://localhost:8080/actuator/health  # Gateway
curl http://localhost:8082/actuator/health  # Catalog
curl http://localhost:8083/actuator/health  # Patron
curl http://localhost:8084/actuator/health  # Circulation
curl http://localhost:8085/actuator/health  # Notification
curl http://localhost:8086/actuator/health  # Integration
curl http://localhost:8087/actuator/health  # Reporting
```

### Database Tests
```bash
# Connect to PostgreSQL
docker exec -it library-postgres psql -U library_user -d library_db

# Check tables
\dt

# Check data
SELECT * FROM marc_records LIMIT 5;
```

### API Tests
```bash
# Test catalog service
curl -X POST http://localhost:8080/api/catalog/records \
  -H "Content-Type: application/json" \
  -d '{"controlNumber":"TEST001","leader":"00000nam a2200000   4500"}'

# Test patron service
curl -X POST http://localhost:8080/api/patrons \
  -H "Content-Type: application/json" \
  -d '{"firstName":"John","lastName":"Doe","email":"john@example.com"}'
```

## Test Results Summary

| Test Category | Total Tests | Passed | Failed | Pending |
|---------------|-------------|--------|--------|---------|
| Service Discovery | 2 | 1 | 0 | 1 |
| Configuration | 2 | 0 | 0 | 2 |
| API Gateway | 2 | 0 | 0 | 2 |
| Database | 2 | 0 | 0 | 2 |
| Catalog Service | 4 | 0 | 0 | 4 |
| Patron Service | 3 | 0 | 0 | 3 |
| Circulation Service | 2 | 0 | 0 | 2 |
| Integration | 2 | 0 | 0 | 2 |
| Performance | 2 | 0 | 0 | 2 |
| Error Handling | 3 | 0 | 0 | 3 |
| Docker | 3 | 0 | 0 | 3 |
| Security | 2 | 0 | 0 | 2 |
| **Total** | **29** | **1** | **0** | **28** |

## Next Steps

1. Execute all pending tests
2. Fix any failures
3. Document test results
4. Create automated test scripts
5. Set up continuous integration 