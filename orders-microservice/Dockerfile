FROM maven:3.9-eclipse-temurin-21 AS builder
WORKDIR /build

COPY core /build/core
RUN mvn -f /build/core/pom.xml clean install -DskipTests

COPY orders-microservice /build/orders-microservice
RUN mvn -f /build/orders-microservice/pom.xml clean package -DskipTests

FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

COPY --from=builder /build/orders-microservice/target/*.jar app.jar

EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]
