FROM openjdk:17-slim as build
WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests -pl config-server -am

FROM openjdk:17-slim
WORKDIR /app
COPY --from=build /app/config-server/target/config-server-1.0-SNAPSHOT.jar app.jar
COPY config-server/src/main/resources/config ./config
EXPOSE 8888
ENTRYPOINT ["java", "-jar", "app.jar"] 