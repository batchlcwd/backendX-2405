FROM openjdk:21-jdk-slim
WORKDIR /app
COPY target/irctc-backend-0.0.1-SNAPSHOT.jar /app/myapp.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "myapp.jar"]