FROM openjdk:16-jdk-alpine
VOLUME /tmp
COPY mysqld.cnf /etc/mysql/mysql.conf.d/mysqld.cnf
COPY ./*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]