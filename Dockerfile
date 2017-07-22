FROM java:8
WORKDIR /data
RUN mkdir config
ADD student-activation-backend-*.jar /data/app.jar
CMD ["java", "-jar", "/data/app.jar"]