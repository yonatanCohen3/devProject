# Use OpenJDK 17 image for runtime
FROM openjdk:21-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the JAR file from the target directory to the Docker image
COPY /target/demo.jar /app

RUN chmod 644 /app/demo.jar && chown nobody:nogroup /app/demo.jar

# Expose the port the application runs on
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "/app/demo.jar"]