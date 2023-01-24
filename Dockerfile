FROM openjdk:14-alpine

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn -f pom.xml clean package

EXPOSE 8080

CMD ["java", "-jar", "target/sweet-stock-api-0.0.1-SNAPSHOT.jar"]
