FROM openjdk:11.0.4-jre-slim-buster
MAINTAINER agostinaluciano
COPY target/crypto-pocket-0.0.1-SNAPSHOT.jar crypto-pocket-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/crypto-pocket-0.0.1-SNAPSHOT.jar"]