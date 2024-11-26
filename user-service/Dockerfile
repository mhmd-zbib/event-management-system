# User Service Dockerfile
FROM maven:3.9.6-eclipse-temurin-21-alpine AS builder
WORKDIR /app

COPY pom.xml .
COPY user-service/pom.xml ./user-service/
RUN mvn dependency:go-offline -B

COPY . .

WORKDIR /app/user-service
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

COPY --from=build /app/user-service/target/*.jar app.jar

RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

EXPOSE 8001

ENTRYPOINT ["java", "-jar", "/app/app.jar"]