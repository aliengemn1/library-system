#!/bin/bash

# Library Management System - Test Runner
# This script runs comprehensive tests for all services

set -e

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

# Test counters
TOTAL_TESTS=0
PASSED_TESTS=0
FAILED_TESTS=0

# Function to print colored output
print_status() {
    local status=$1
    local message=$2
    case $status in
        "PASS")
            echo -e "${GREEN}✅ PASS${NC}: $message"
            ((PASSED_TESTS++))
            ;;
        "FAIL")
            echo -e "${RED}❌ FAIL${NC}: $message"
            ((FAILED_TESTS++))
            ;;
        "INFO")
            echo -e "${BLUE}ℹ️  INFO${NC}: $message"
            ;;
        "WARN")
            echo -e "${YELLOW}⚠️  WARN${NC}: $message"
            ;;
    esac
    ((TOTAL_TESTS++))
}

# Function to test HTTP endpoint
test_endpoint() {
    local url=$1
    local description=$2
    local expected_status=${3:-200}
    
    if curl -s -f "$url" > /dev/null 2>&1; then
        print_status "PASS" "$description"
        return 0
    else
        print_status "FAIL" "$description (URL: $url)"
        return 1
    fi
}

# Function to wait for service to be ready
wait_for_service() {
    local url=$1
    local service_name=$2
    local max_attempts=30
    local attempt=1
    
    print_status "INFO" "Waiting for $service_name to be ready..."
    
    while [ $attempt -le $max_attempts ]; do
        if curl -s -f "$url" > /dev/null 2>&1; then
            print_status "PASS" "$service_name is ready"
            return 0
        fi
        
        echo -n "."
        sleep 2
        ((attempt++))
    done
    
    print_status "FAIL" "$service_name failed to start within timeout"
    return 1
}

# Function to check Docker container status
check_container() {
    local container_name=$1
    local service_name=$2
    
    if docker ps --format "table {{.Names}}\t{{.Status}}" | grep -q "$container_name.*Up"; then
        print_status "PASS" "$service_name container is running"
        return 0
    else
        print_status "FAIL" "$service_name container is not running"
        return 1
    fi
}

# Function to check container logs for errors
check_container_logs() {
    local container_name=$1
    local service_name=$2
    
    if docker logs "$container_name" 2>&1 | grep -i "error" > /dev/null; then
        print_status "WARN" "$service_name has errors in logs"
        return 1
    else
        print_status "PASS" "$service_name logs are clean"
        return 0
    fi
}

# Main test execution
main() {
    echo -e "${BLUE}================================${NC}"
    echo -e "${BLUE}  Library System Test Runner${NC}"
    echo -e "${BLUE}================================${NC}"
    echo ""
    
    # Check if Docker is running
    if ! docker info > /dev/null 2>&1; then
        print_status "FAIL" "Docker is not running"
        exit 1
    fi
    
    # Check if docker-compose is available
    if ! command -v docker-compose > /dev/null 2>&1; then
        print_status "FAIL" "docker-compose is not installed"
        exit 1
    fi
    
    print_status "INFO" "Starting tests..."
    echo ""
    
    # 1. Check if services are running
    print_status "INFO" "Checking service status..."
    
    check_container "library-postgres" "PostgreSQL"
    check_container "library-discovery-server" "Discovery Server"
    check_container "library-config-server" "Config Server"
    check_container "library-catalog-service" "Catalog Service"
    check_container "library-patron-service" "Patron Service"
    check_container "library-circulation-service" "Circulation Service"
    check_container "library-notification-service" "Notification Service"
    check_container "library-integration-service" "Integration Service"
    check_container "library-reporting-service" "Reporting Service"
    check_container "library-api-gateway" "API Gateway"
    
    echo ""
    
    # 2. Wait for services to be ready
    print_status "INFO" "Waiting for services to be ready..."
    
    wait_for_service "http://localhost:8761/actuator/health" "Discovery Server"
    wait_for_service "http://localhost:8888/actuator/health" "Config Server"
    wait_for_service "http://localhost:8082/actuator/health" "Catalog Service"
    wait_for_service "http://localhost:8083/actuator/health" "Patron Service"
    wait_for_service "http://localhost:8084/actuator/health" "Circulation Service"
    wait_for_service "http://localhost:8085/actuator/health" "Notification Service"
    wait_for_service "http://localhost:8086/actuator/health" "Integration Service"
    wait_for_service "http://localhost:8087/actuator/health" "Reporting Service"
    wait_for_service "http://localhost:8080/actuator/health" "API Gateway"
    
    echo ""
    
    # 3. Test health endpoints
    print_status "INFO" "Testing health endpoints..."
    
    test_endpoint "http://localhost:8761/actuator/health" "Discovery Server Health"
    test_endpoint "http://localhost:8888/actuator/health" "Config Server Health"
    test_endpoint "http://localhost:8082/actuator/health" "Catalog Service Health"
    test_endpoint "http://localhost:8083/actuator/health" "Patron Service Health"
    test_endpoint "http://localhost:8084/actuator/health" "Circulation Service Health"
    test_endpoint "http://localhost:8085/actuator/health" "Notification Service Health"
    test_endpoint "http://localhost:8086/actuator/health" "Integration Service Health"
    test_endpoint "http://localhost:8087/actuator/health" "Reporting Service Health"
    test_endpoint "http://localhost:8080/actuator/health" "API Gateway Health"
    
    echo ""
    
    # 4. Test Eureka dashboard
    print_status "INFO" "Testing Eureka dashboard..."
    test_endpoint "http://localhost:8761" "Eureka Dashboard"
    
    echo ""
    
    # 5. Test database connection
    print_status "INFO" "Testing database connection..."
    if docker exec library-postgres pg_isready -U library_user -d library_db > /dev/null 2>&1; then
        print_status "PASS" "PostgreSQL is ready to accept connections"
    else
        print_status "FAIL" "PostgreSQL is not ready"
    fi
    
    echo ""
    
    # 6. Test API Gateway routes
    print_status "INFO" "Testing API Gateway routes..."
    
    # Test catalog service through gateway
    if curl -s -f "http://localhost:8080/api/catalog/records" > /dev/null 2>&1; then
        print_status "PASS" "API Gateway - Catalog Service route"
    else
        print_status "FAIL" "API Gateway - Catalog Service route"
    fi
    
    # Test patron service through gateway
    if curl -s -f "http://localhost:8080/api/patrons" > /dev/null 2>&1; then
        print_status "PASS" "API Gateway - Patron Service route"
    else
        print_status "FAIL" "API Gateway - Patron Service route"
    fi
    
    echo ""
    
    # 7. Test basic CRUD operations
    print_status "INFO" "Testing basic CRUD operations..."
    
    # Test creating a MARC record
    MARC_RESPONSE=$(curl -s -w "%{http_code}" -X POST http://localhost:8080/api/catalog/records \
        -H "Content-Type: application/json" \
        -d '{"controlNumber":"TEST001","leader":"00000nam a2200000   4500"}' \
        -o /dev/null)
    
    if [ "$MARC_RESPONSE" = "201" ] || [ "$MARC_RESPONSE" = "200" ]; then
        print_status "PASS" "Create MARC record"
    else
        print_status "FAIL" "Create MARC record (HTTP $MARC_RESPONSE)"
    fi
    
    # Test creating a patron
    PATRON_RESPONSE=$(curl -s -w "%{http_code}" -X POST http://localhost:8080/api/patrons \
        -H "Content-Type: application/json" \
        -d '{"firstName":"John","lastName":"Doe","email":"john@example.com"}' \
        -o /dev/null)
    
    if [ "$PATRON_RESPONSE" = "201" ] || [ "$PATRON_RESPONSE" = "200" ]; then
        print_status "PASS" "Create patron"
    else
        print_status "FAIL" "Create patron (HTTP $PATRON_RESPONSE)"
    fi
    
    echo ""
    
    # 8. Check container logs for errors
    print_status "INFO" "Checking container logs for errors..."
    
    check_container_logs "library-discovery-server" "Discovery Server"
    check_container_logs "library-config-server" "Config Server"
    check_container_logs "library-catalog-service" "Catalog Service"
    check_container_logs "library-patron-service" "Patron Service"
    check_container_logs "library-circulation-service" "Circulation Service"
    check_container_logs "library-notification-service" "Notification Service"
    check_container_logs "library-integration-service" "Integration Service"
    check_container_logs "library-reporting-service" "Reporting Service"
    check_container_logs "library-api-gateway" "API Gateway"
    
    echo ""
    
    # 9. Performance check
    print_status "INFO" "Checking container resource usage..."
    
    # Check if containers are using reasonable resources
    TOTAL_MEMORY=$(docker stats --no-stream --format "table {{.MemUsage}}" | grep -v "MEM USAGE" | awk '{print $1}' | sed 's/MiB//' | awk '{sum+=$1} END {print sum}')
    
    if [ "$TOTAL_MEMORY" -lt 2000 ]; then
        print_status "PASS" "Memory usage is reasonable (${TOTAL_MEMORY}MB)"
    else
        print_status "WARN" "High memory usage (${TOTAL_MEMORY}MB)"
    fi
    
    echo ""
    
    # 10. Summary
    echo -e "${BLUE}================================${NC}"
    echo -e "${BLUE}  Test Results Summary${NC}"
    echo -e "${BLUE}================================${NC}"
    echo ""
    echo -e "Total Tests: ${TOTAL_TESTS}"
    echo -e "${GREEN}Passed: ${PASSED_TESTS}${NC}"
    echo -e "${RED}Failed: ${FAILED_TESTS}${NC}"
    echo -e "${YELLOW}Warnings: $((TOTAL_TESTS - PASSED_TESTS - FAILED_TESTS))${NC}"
    echo ""
    
    if [ $FAILED_TESTS -eq 0 ]; then
        echo -e "${GREEN}🎉 All critical tests passed!${NC}"
        exit 0
    else
        echo -e "${RED}❌ Some tests failed. Please check the logs above.${NC}"
        exit 1
    fi
}

# Run main function
main "$@" 