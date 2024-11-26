# Stage 1: Build the application
FROM maven:3.9.4-eclipse-temurin-21 AS builder
WORKDIR /build

# Copy parent POM and child service POM
COPY ../pom.xml ./
COPY pom.xml ./provider-service/

# Resolve dependencies
RUN mvn dependency:go-offline -B

# Copy source code
COPY src ./provider-service/src

# Build the service
RUN mvn clean package -f provider-service/pom.xml -DskipTests

# Stage 2: Create the runtime image
FROM amazoncorretto:21
WORKDIR /app

# Copy the built JAR from the builder stage
COPY --from=builder /build/provider-service/target/provider-service.jar ./provider-service.jar

# Expose the service's port
EXPOSE 8002

# Command to run the application
ENTRYPOINT ["java", "-jar", "provider-service.jar"]
