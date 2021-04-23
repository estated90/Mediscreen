FROM openjdk:16-jdk-alpine
VOLUME /tmp
COPY ./*.jar app.jar
COPY mysqld.cnf /etc/mysql/mysql.conf.d/mysqld.cnf
ENTRYPOINT ["java","-jar","app.jar"]