# syntax=docker/dockerfile:1.7

FROM gradle:8.5-jdk17 AS build
WORKDIR /app


COPY settings.gradle build.gradle gradlew ./
COPY gradle/ gradle/

RUN --mount=type=cache,target=/home/gradle/.gradle \
    ./gradlew --no-daemon build -x test --dry-run


COPY src/ src/

RUN --mount=type=cache,target=/home/gradle/.gradle \
    ./gradlew --no-daemon test


RUN --mount=type=cache,target=/home/gradle/.gradle \
    ./gradlew --no-daemon clean bootJar -x test


FROM eclipse-temurin:17-jre
WORKDIR /app

COPY --from=build /app/build/libs/*.jar franquiciacore.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","franquiciacore.jar"]
