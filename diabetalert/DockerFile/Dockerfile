FROM openjdk:16-jdk-alpine
VOLUME /tmp
COPY ./*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]