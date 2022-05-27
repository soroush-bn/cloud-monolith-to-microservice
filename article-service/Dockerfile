FROM openjdk:17-jdk-alpine
MAINTAINER cloudcomp
COPY target/docker-article-service-1.0.0.jar article-service.jar
ENTRYPOINT ["java","-jar","/article-service.jar"]