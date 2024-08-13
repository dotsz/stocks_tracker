# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the Maven wrapper files separately to ensure they are in the correct location
COPY mvnw mvnw
COPY .mvn .mvn

# Copy the rest of the project files to the container
COPY . .

# Ensure the Maven wrapper has execute permissions (for Unix-based systems)
RUN chmod +x ./mvnw

# Build the application using the Maven wrapper
RUN ./mvnw clean package -DskipTests

# Expose the port on which the app will run
EXPOSE 8080

# Run the JAR file
CMD ["sh", "-c", "java -Dserver.port=${PORT} -jar target/stocks_tracker-0.0.1-SNAPSHOT.jar"]
