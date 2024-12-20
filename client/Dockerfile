# Etapa de construcción
# Use the official image as a parent image
FROM openjdk:17-slim as builder

# Set the working directory in the builder container
WORKDIR /usr/src/app

# Copy the source code from your local host to the builder container
COPY . .

# Convert gradlew to Unix format and make it executable
RUN sed -i 's/\r//' gradlew && chmod +x gradlew

# Build the application without running tests
RUN ./gradlew build -x test

# Etapa de ejecución
# Use the official image as a parent image for the runtime stage
FROM openjdk:17-slim

# Set the working directory in the new container to /usr/src/app
WORKDIR /usr/src/app

# Copy the built jar file from the builder stage
COPY --from=builder /usr/src/app/build/libs/*.jar ./app.jar

# Set the default command to run your application
# Utiliza el script de espera antes de iniciar la aplicación
CMD ["java", "-jar", "./app.jar"]
