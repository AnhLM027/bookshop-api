# Stage 1: Build
FROM gradle:8.14-jdk17 AS build
WORKDIR /backend
COPY . .
RUN gradle clean bootJar -x test --no-daemon

# Stage 2: Run
FROM eclipse-temurin:17-jre
WORKDIR /app
EXPOSE 8080
COPY --from=build /backend/build/libs/bookshop.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
