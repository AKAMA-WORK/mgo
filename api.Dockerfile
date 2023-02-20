FROM openjdk:17-jdk-alpine3.14 as builder

WORKDIR /app

COPY . /app/

RUN chmod +x /app/gradlew

RUN /app/gradlew

RUN /app/gradlew api:quarkusBuild



FROM openjdk:17-jdk-alpine3.14

WORKDIR /app

COPY --from=builder /app/api/build/  ./api/

RUN ls -l ./api/

RUN java -jar /app/api/api-1.0.0-SNAPSHOT-runner.jar
