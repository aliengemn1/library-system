FROM openjdk:17-slim as build
WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests -pl admin-service -am

FROM openjdk:17-slim
WORKDIR /app
COPY --from=build /app/admin-service/target/admin-service-1.0-SNAPSHOT.jar app.jar
EXPOSE 8088
HEALTHCHECK --interval=30s --timeout=3s --start-period=60s --retries=3 \
  CMD curl -f http://localhost:8088/actuator/health || exit 1
ENTRYPOINT ["java", "-jar", "app.jar"] 