# Use an official OpenJDK runtime as a parent image
FROM openjdk:21-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file into the container
COPY target/stocks_tracker-0.0.1-SNAPSHOT.jar /app/stocks_tracker.jar

# Expose the port that the app will run on
EXPOSE 8080

# Set up an entry point to run the JAR file
ENTRYPOINT ["java", "-jar", "/app/stocks_tracker.jar"]


