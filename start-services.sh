#!/bin/bash

echo "Starting Library Management System - Microservices Architecture"
echo "=============================================================="

# Function to check if a port is in use
check_port() {
    if lsof -Pi :$1 -sTCP:LISTEN -t >/dev/null ; then
        echo "Port $1 is already in use. Please stop the service using this port first."
        exit 1
    fi
}

# Check if ports are available
echo "Checking port availability..."
check_port 8761
check_port 8888
check_port 8080
check_port 8081

echo "All ports are available. Starting services..."

# Start Discovery Server
echo "Starting Discovery Server (Eureka) on port 8761..."
cd discovery-server
mvn spring-boot:run > ../logs/discovery-server.log 2>&1 &
DISCOVERY_PID=$!
cd ..

# Wait for discovery server to start
echo "Waiting for Discovery Server to start..."
sleep 10

# Start Configuration Server
echo "Starting Configuration Server on port 8888..."
cd config-server
mvn spring-boot:run > ../logs/config-server.log 2>&1 &
CONFIG_PID=$!
cd ..

# Wait for config server to start
echo "Waiting for Configuration Server to start..."
sleep 10

# Start Library Service
echo "Starting Library Service on port 8081..."
cd library-service
mvn spring-boot:run > ../logs/library-service.log 2>&1 &
LIBRARY_PID=$!
cd ..

# Wait for library service to start
echo "Waiting for Library Service to start..."
sleep 15

# Start API Gateway
echo "Starting API Gateway on port 8080..."
cd api-gateway
mvn spring-boot:run > ../logs/api-gateway.log 2>&1 &
GATEWAY_PID=$!
cd ..

# Create logs directory if it doesn't exist
mkdir -p logs

# Save PIDs to file for later cleanup
echo $DISCOVERY_PID > logs/discovery-server.pid
echo $CONFIG_PID > logs/config-server.pid
echo $LIBRARY_PID > logs/library-service.pid
echo $GATEWAY_PID > logs/api-gateway.pid

echo "=============================================================="
echo "All services started successfully!"
echo ""
echo "Service URLs:"
echo "- Eureka Dashboard: http://localhost:8761"
echo "- API Gateway: http://localhost:8080"
echo "- H2 Database Console: http://localhost:8081/h2-console"
echo ""
echo "Log files are available in the logs/ directory"
echo "To stop all services, run: ./stop-services.sh"
echo "==============================================================" 