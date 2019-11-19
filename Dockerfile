FROM openjdk:8-jdk-slim

COPY . /app

WORKDIR /app

RUN ./mvnw package

EXPOSE 4006

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","target/phets-users-ms-1.jar"]