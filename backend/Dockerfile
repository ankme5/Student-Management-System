FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/*.jar .
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "backend-0.0.1.jar"]
