FROM openjdk:11-jdk-slim as build
MAINTAINER uit.com
copy target/user_service-0.0.1-SNAPSHOT.jar user_service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/user_service-0.0.1-SNAPSHOT.jar"]