FROM eclipse-temurin:21-jdk-ubi10-minimal

# Set working directory
WORKDIR /app

COPY . .

RUN ls -al

RUN ./gradlew build

EXPOSE 8080

CMD ["java", "-jar", "./build/libs/bexbe-project-backend-1.0-SNAPSHOT.jar"]