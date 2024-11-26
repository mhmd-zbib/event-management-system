# Stage 1: Build the application
FROM maven:3.9.6-eclipse-temurin-21-alpine AS builder
WORKDIR /app

# Copy parent POM and user-service POM
COPY pom.xml .
COPY user-service/pom.xml ./user-service/

# Resolve dependencies
RUN mvn dependency:go-offline -B

# Copy the rest of the code
COPY . .

# Build the application (in user-service directory)
WORKDIR /app/user-service
RUN mvn clean package -DskipTests

# Stage 2: Create the runtime image
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Copy the built JAR from the builder stage
COPY --from=builder /app/user-service/target/*.jar app.jar

# Set up a non-root user for security
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

# Expose the port the app will run on
EXPOSE 8001

# Command to run the application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
