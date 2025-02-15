# Use a lightweight Java image
FROM openjdk:17-jdk-slim

# Set working directory inside the container
WORKDIR /app

# Copy Maven project files and dependencies
COPY pom.xml mvnw ./
COPY .mvn .mvn
RUN ./mvnw dependency:go-offline

# Copy the source code and build the application
COPY target/*.jar app.jar

# Expose the application port
EXPOSE 8088

# Run the application
CMD ["java", "-jar", "app.jar"]