FROM openjdk:17-slim as build
WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests -pl circulation-service -am

FROM openjdk:17-slim
WORKDIR /app
COPY --from=build /app/circulation-service/target/circulation-service-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"] 