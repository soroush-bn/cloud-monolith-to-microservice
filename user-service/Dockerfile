FROM openjdk:17-jdk-alpine
MAINTAINER cloudcomp
COPY target/docker-user-service-1.0.0.jar user-service.jar
ENTRYPOINT ["java","-jar","/user-service.jar"]