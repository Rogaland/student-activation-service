FROM gradle:2.13-jdk8-alpine as java
USER root
COPY . .
RUN gradle --no-daemon build

FROM openjdk:8-jre-alpine
WORKDIR /data
RUN mkdir config
COPY --from=java /home/gradle/build/libs/student-activation-backend-*.jar /data/app.jar
CMD ["java", "-jar", "/data/app.jar"]
