# Use Java 21 runtime

FROM eclipse-temurin:21-jdk

# Set working directory

WORKDIR /app

# Copy project files

COPY . .

# Build application

RUN apt-get update && apt-get install -y maven

RUN mvn clean package -DskipTests

# Expose Spring Boot port

EXPOSE 8080

# Run application

CMD ["java", "-jar", "target/scicalculator-0.0.1-SNAPSHOT.jar"]