# Build stage
FROM maven:3-openjdk-17 as build
WORKDIR /app

COPY pom.xml .
COPY src/ src/
RUN mvn package

# Runtime stage
FROM docker.io/openjdk:17-jdk
COPY --from=build /app/target/mortgage-0.0.1-SNAPSHOT.jar /app/mortgage-0.0.1.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/mortgage-0.0.1.jar"]
