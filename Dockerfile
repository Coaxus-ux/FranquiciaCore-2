FROM gradle:8.5-jdk17 AS build

WORKDIR /app
COPY build.gradle .
COPY settings.gradle .
COPY gradlew .
COPY gradle ./gradle
COPY src ./src
RUN gradle build -x test

# 2) Runtime con Java 17
FROM eclipse-temurin:17-jre

WORKDIR /app
# Copiamos el JAR de franquiciacore
COPY --from=build /app/build/libs/*.jar franquiciacore.jar

EXPOSE 8080
CMD ["java", "-jar", "franquiciacore.jar"]
