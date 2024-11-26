# Use the official OpenJDK 21 image as a base image
FROM openjdk:21-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file from the target directory
COPY target/*.jar app.jar

# Expose the default port for Spring Boot apps
EXPOSE 8761

# Run the JAR file when the container starts
ENTRYPOINT ["java", "-jar", "app.jar"]
