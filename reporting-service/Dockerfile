FROM openjdk:17-slim as build
WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests -pl reporting-service -am

FROM openjdk:17-slim
WORKDIR /app
COPY --from=build /app/reporting-service/target/reporting-service-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"] 