FROM maven:3.9-eclipse-temurin-21 AS builder
WORKDIR /build

COPY core /build/core
RUN mvn -f /build/core/pom.xml clean install -DskipTests

COPY inventory-microservice /build/inventory-microservice
RUN mvn -f /build/inventory-microservice/pom.xml clean package -DskipTests

FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

COPY --from=builder /build/inventory-microservice/target/*.jar app.jar

EXPOSE 8082
ENTRYPOINT ["java", "-jar", "app.jar"]
