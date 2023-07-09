FROM maven:3.6.3-jdk-11 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:11-jdk-slim-sid
COPY --from=build /target/e-commerce-0.0.1-SNAPSHOT.jar e-commerce.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "e-commerce.jar"]
