# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the Maven project files to the container
COPY . .

# Build the application using Maven
RUN ./mvnw clean package -DskipTests

# Copy the built JAR file to the final location
COPY target/stocks_tracker-0.0.1-SNAPSHOT.jar app.jar

# Expose the port on which the app will run
EXPOSE 8080

# Run the JAR file
CMD ["java", "-Dserver.port=$PORT", "-jar", "app.jar"]
