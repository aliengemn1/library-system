#!/bin/bash

# Library Management System - Startup Script
# This script builds and starts all services

set -e

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

echo -e "${BLUE}================================${NC}"
echo -e "${BLUE}  Library System Startup${NC}"
echo -e "${BLUE}================================${NC}"
echo ""

# Check if Docker is running
if ! docker info > /dev/null 2>&1; then
    echo -e "${RED}❌ Docker is not running. Please start Docker first.${NC}"
    exit 1
fi

# Check if docker-compose is available
if ! command -v docker-compose > /dev/null 2>&1; then
    echo -e "${RED}❌ docker-compose is not installed. Please install it first.${NC}"
    exit 1
fi

echo -e "${BLUE}ℹ️  Building and starting all services...${NC}"
echo ""

# Stop any existing containers
echo -e "${YELLOW}⚠️  Stopping any existing containers...${NC}"
docker-compose down --remove-orphans

# Build and start all services
echo -e "${BLUE}ℹ️  Building and starting services...${NC}"
docker-compose up --build -d

echo ""
echo -e "${GREEN}✅ Services are starting up...${NC}"
echo ""
echo -e "${BLUE}ℹ️  Service URLs:${NC}"
echo -e "  Discovery Server: ${GREEN}http://localhost:8761${NC}"
echo -e "  Config Server:    ${GREEN}http://localhost:8888${NC}"
echo -e "  API Gateway:      ${GREEN}http://localhost:8080${NC}"
echo -e "  Catalog Service:  ${GREEN}http://localhost:8082${NC}"
echo -e "  Patron Service:   ${GREEN}http://localhost:8083${NC}"
echo -e "  Circulation:      ${GREEN}http://localhost:8084${NC}"
echo -e "  Notification:     ${GREEN}http://localhost:8085${NC}"
echo -e "  Integration:      ${GREEN}http://localhost:8086${NC}"
echo -e "  Reporting:        ${GREEN}http://localhost:8087${NC}"
echo ""
echo -e "${BLUE}ℹ️  PostgreSQL: localhost:5432${NC}"
echo ""

# Wait a moment for services to start
echo -e "${YELLOW}⚠️  Waiting for services to initialize...${NC}"
sleep 10

# Check service status
echo -e "${BLUE}ℹ️  Checking service status...${NC}"
docker-compose ps

echo ""
echo -e "${GREEN}🎉 System startup complete!${NC}"
echo ""
echo -e "${BLUE}ℹ️  Useful commands:${NC}"
echo -e "  View logs:        ${YELLOW}docker-compose logs -f${NC}"
echo -e "  Stop services:    ${YELLOW}docker-compose down${NC}"
echo -e "  Run tests:        ${YELLOW}./run-tests.sh${NC}"
echo -e "  Restart services: ${YELLOW}docker-compose restart${NC}"
echo ""
echo -e "${BLUE}ℹ️  To run tests, execute: ${YELLOW}./run-tests.sh${NC}" 