# Stage 1 : BUILD
FROM maven:3.8.5-openjdk-17-slim as build
WORKDIR /app
COPY src ./src
COPY pom.xml .
RUN mvn clean install -DskipTests

# Stage 2 : RUN
FROM maven:3.8.5-openjdk-17-slim as run
WORKDIR /app
COPY --from=build /app/target/backend-0.0.1.jar .
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "backend-0.0.1.jar"]
