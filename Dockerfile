FROM openjdk:11
FROM maven:3.6.3

WORKDIR /app

COPY . /app

RUN mvn -v
RUN mvn clean
RUN mvn install -DskipTests
EXPOSE 8080

ADD target/task-0.0.1-SNAPSHOT.jar task-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","task-0.0.1-SNAPSHOT.jar"]
