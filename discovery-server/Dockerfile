FROM openjdk:17-slim as build
WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests -pl discovery-server -am

FROM openjdk:17-slim
WORKDIR /app
COPY --from=build /app/discovery-server/target/discovery-server-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"] 