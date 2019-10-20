FROM openjdk:8-jdk-slim

COPY target/phets-users-ms-1.jar /app.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]