FROM openjdk:14-alpine

WORKDIR /app

RUN apk add --no-cache maven
ENV PATH /usr/bin/mvn:$PATH

COPY pom.xml .
COPY src ./src

RUN mvn -f pom.xml clean package

CMD ["java", "-jar", "target/sweet-stock-api-0.0.1-SNAPSHOT.jar"]
