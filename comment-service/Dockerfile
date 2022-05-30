FROM openjdk:17-jdk-alpine
MAINTAINER cloudcomp
COPY target/docker-comment-service-1.0.0.jar comment-service.jar
ENTRYPOINT ["java","-jar","/comment-service.jar"]