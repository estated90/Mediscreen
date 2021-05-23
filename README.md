<h1 align="center">Welcome to Mediscreen Project 👋</h1>
<p>
  <img alt="Version" src="https://img.shields.io/badge/version-1.0.0-blue.svg?cacheSeconds=2592000" />
</p>

> Application that manage microservices. The microservices will manage the patient and their information, the historic of the patient and finally it will be able to assert a diabet risk. The application work with one front. Project 9 of OpenClassroom.

## Install

### Build the JAR files

For personalInfo :

```
cd personalInfo/
```

```
mvn clean
```

```
mvn package
```

```sh
move target\personalinfo-0.0.1-SNAPSHOT.jar DockerFile\
```

for diabetalert :

```
cd diabetalert/
```

```
mvn clean
```

```
mvn package
```

```sh
move target\diabetalert-0.0.1-SNAPSHOT.jar DockerFile\
```

for historic:

```
cd historic/
```

```
mvn clean
```

```
mvn package
```

```sh
move target\historic-0.0.1-SNAPSHOT.jar DockerFile\
```

### Build docker container

```
docker-compose up -d
```

## Usage

```sh
The docker will host 6 countainer for the applications and their DB
```

## 

1. Install 

   [Java]: https://www.oracle.com/java/technologies/javase-jdk16-downloads.html	"java 16 installation guide"

2. Install 

   [Maven]: http://maven.apache.org/download.cgi	"Maven installation guide"

3. Install Docker app : 

   [here]: https://docs.docker.com/docker-for-windows/install/	"Docker install for windows"

One Docker with 5 containers will be created.

- personalInfo: localhost:8081
- historic: localhost:8082
- diabetAlert: localhost:8083
- mediscreen-db: localhost:3306
- historic-db: localhost:27017
- mediscreenfront : localhost:4200

## API

personalInfo:

- http://localhost:8080/swagger-ui.html#/

historic:

- http://localhost:8081/swagger-ui.html#/

RewardsService:

- http://localhost:8082/swagger-ui.html#/

diabetAlert:

- http://localhost:8083/swagger-ui.html#/

## Author

👤 **Nicolas**

* Github: [@Estated90](https://github.com/Estated90)

## Show your support

Give a ⭐️ if this project helped you!

***
_This README was generated with ❤️ by [readme-md-generator](https://github.com/kefranabg/readme-md-generator)_