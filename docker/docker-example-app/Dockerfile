FROM openjdk:21-jdk-slim
WORKDIR /app
COPY target/docker-example-app-0.0.1-SNAPSHOT.jar /app/myapp.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "myapp.jar"]