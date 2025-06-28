FROM openjdk:17-slim as build
WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests -pl integration-service -am

FROM openjdk:17-slim
WORKDIR /app
COPY --from=build /app/integration-service/target/integration-service-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"] 