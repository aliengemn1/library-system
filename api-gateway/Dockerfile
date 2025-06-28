FROM openjdk:17-slim as build
WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests -pl api-gateway -am

FROM openjdk:17-slim
WORKDIR /app
COPY --from=build /app/api-gateway/target/api-gateway-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"] 