FROM openjdk:11
COPY . /app
WORKDIR /app
RUN mvn clean install
RUN mvn package
ENTRYPOINT ["java", "-jar", "target/sweet-stock-api-0.0.1-SNAPSHOT.jar"]
