#!/bin/bash

echo "Stopping Library Management System - Microservices Architecture"
echo "=============================================================="

# Function to stop service by PID
stop_service() {
    local service_name=$1
    local pid_file="logs/${service_name}.pid"
    
    if [ -f "$pid_file" ]; then
        local pid=$(cat "$pid_file")
        if ps -p $pid > /dev/null 2>&1; then
            echo "Stopping $service_name (PID: $pid)..."
            kill $pid
            rm "$pid_file"
        else
            echo "$service_name is not running."
            rm "$pid_file"
        fi
    else
        echo "$service_name PID file not found."
    fi
}

# Stop services in reverse order
echo "Stopping API Gateway..."
stop_service "api-gateway"

echo "Stopping Library Service..."
stop_service "library-service"

echo "Stopping Configuration Server..."
stop_service "config-server"

echo "Stopping Discovery Server..."
stop_service "discovery-server"

echo "=============================================================="
echo "All services stopped successfully!"
echo "==============================================================" 