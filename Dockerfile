FROM openjdk:11

WORKDIR /app

COPY pom.xml .
COPY src ./src

EXPOSE 8080

RUN mvn clean
RUN mvn spring-boot:run
