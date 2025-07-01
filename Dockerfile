# Use OpenJDK image as base
FROM public.ecr.aws/docker/library/openjdk:21-jdk-slim

# Set working directory
WORKDIR /app

# Copy the jar file to the container
COPY target/*.jar app.jar

# Expose the port your app runs on
EXPOSE 8082

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
