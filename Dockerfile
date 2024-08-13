# Use an official OpenJDK 22 runtime as a parent image (if available)
FROM openjdk:22-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the project’s JAR file into the container at /app
COPY target/stocks_tracker-0.0.1-SNAPSHOT.jar app.jar

# Expose the port on which the app will run
EXPOSE 8080

# Run the JAR file
CMD ["java", "-Dserver.port=$PORT", "-jar", "app.jar"]
