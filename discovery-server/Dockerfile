# Stage 1: Build the application
FROM maven:3.9.4-eclipse-temurin-21 AS builder
WORKDIR /build

# Copy parent POM and child service POM
COPY ../pom.xml ./
COPY pom.xml ./discovery-server/

# Resolve dependencies
RUN mvn dependency:go-offline -B

# Copy source code
COPY src ./discovery-server/src

# Build the service
RUN mvn clean package -f discovery-server/pom.xml -DskipTests

# Stage 2: Create the runtime image
FROM amazoncorretto:21
WORKDIR /app

# Copy the built JAR from the builder stage
COPY --from=builder /build/discovery-server/target/discovery-server.jar ./discovery-server.jar

# Expose the service's port
EXPOSE 8761

# Command to run the application
ENTRYPOINT ["java", "-jar", "discovery-server.jar"]
