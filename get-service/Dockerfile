FROM gradle:8.5 as build
WORKDIR /app

COPY src src
COPY settings.gradle build.gradle gradlew ./
COPY gradle gradle

RUN gradle --no-daemon dependencies && \
    gradle --no-daemon bootJar

FROM bellsoft/liberica-openjre-alpine-musl:17
WORKDIR /app

ARG DEPENDENCY=/app/build/libs
COPY --from=build ${DEPENDENCY}/*.jar app.jar
CMD ["java", "-jar", "app.jar"]