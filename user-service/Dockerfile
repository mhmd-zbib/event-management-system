FROM openjdk:21-jdk-slim

WORKDIR /app

COPY target/user-service-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8001

ENTRYPOINT ["java", "-jar", "app.jar"]
