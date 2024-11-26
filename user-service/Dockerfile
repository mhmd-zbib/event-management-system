# Stage 1: Build the application
FROM maven:3.9.4-eclipse-temurin-21 AS builder
WORKDIR /build

# Copy parent POM and child service POM
COPY ../pom.xml ./
COPY pom.xml ./user-service/

# Resolve dependencies
RUN mvn dependency:go-offline -B

# Copy source code
COPY src ./user-service/src

# Build the service
RUN mvn clean package -f user-service/pom.xml -DskipTests

# Stage 2: Create the runtime image
FROM amazoncorretto:21
WORKDIR /app

# Copy the built JAR from the builder stage
COPY --from=builder /build/user-service/target/user-service.jar ./user-service.jar

# Expose the service's port
EXPOSE 8001

# Command to run the application
ENTRYPOINT ["java", "-jar", "user-service.jar"]
