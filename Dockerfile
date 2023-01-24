FROM openjdk:11

COPY pom.xml .
COPY src ./src

COPY . /app

EXPOSE 8080

WORKDIR /app
RUN mvn -f pom.xml clean package
ENTRYPOINT ["java", "-jar", "target/sweet-stock-api-0.0.1-SNAPSHOT.jar"]
