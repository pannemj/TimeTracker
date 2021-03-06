FROM openjdk:8-jre-alpine
LABEL maintainer="pannemj@gmail.com"
EXPOSE 8085 
COPY target/TimeTracker*.jar /test/app.jar
WORKDIR /
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/test/app.jar"]