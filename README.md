# TimeTracker
<h1 align="center"><a href="https://github.com/pannemj/TimeTracker" target="_blank">Employee Time Tracker</a></h1>

<p align="center">
  <a href="https://www.oracle.com/technetwork/java/javase/downloads/index.html"><img alt="JDK" src="https://img.shields.io/badge/JDK-1.8.0_162-orange.svg"/></a>
  <a href="https://docs.spring.io/spring-boot/docs/2.1.0.RELEASE/reference/html/"><img alt="Spring Boot" src="https://img.shields.io/badge/Spring Boot-2.1.0.RELEASE-brightgreen.svg"/></a>
</p>

<p align="center">
  <a href="https://github.com/balaji-singh/employee-time-tracker/stargazers"><img alt="star" src="https://img.shields.io/github/stars/balaji-singh/employee-time-tracker.svg?label=Stars&style=social"/></a>
  <a href="https://github.com/balaji-singh/employee-time-tracker/network/members"><img alt="star" src="https://img.shields.io/github/forks/balaji-singh/employee-time-tracker.svg?label=Fork&style=social"/></a>
  <a href="https://github.com/balaji-singh/employee-time-tracker/watchers"><img alt="star" src="https://img.shields.io/github/watchers/balaji-singh/employee-time-tracker.svg?label=Watch&style=social"/></a>
</p>

## Introduction
A Web based application to track Employee Time

Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.
Below are the instructions to copy the project up and running on your local machine.

## Getting Started

1. `git clone https://github.com/pannemj/TimeTracker`
2. Open the cloned project in STS
3. Import the `pom.xml` file from the root directory using `Maven Projects` panel
4. If you can not find `Maven Projects` panel, try to tick `View -> Tool Buttons` on and the `Maven Projects` panel will appear on the right side of STS.

| Module Name                                                  | Module Description                                           |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| [TimeTracker](./TimeTracker) | a web based application to track Employee activities developed by using Spring Boot , Thymleaf , Jquery , Bootstrap ,DataTable .                                           |

## Prerequisites
What things you need to install the software and how to install them.

- **IDE** - Eclipse,VS Studio Code,Spring Tool Suit,IntelliJ IDEA. 

- **Language**- Java.

- **Frameworks**- Spring Boot,thymeleaf , AOP , Jquery , Bootstrap

- **Logging**- Spring Boot SLF4j Logback.

It will sue for caputring and archiving the generated logs.

- **Tools**- Git.

Git is a version controlling tool. 

open terminal and use below  command to clone the repository from gitgub.

git clone https://github.com/pannemj/TimeTracker.git.

Open your favorite IDE then import existed maven projects into workspace. 

        (or)

Open Your favorite IDE configure Egit then clone the repository and import this project into to workspace.


- **Build management**- Maven.

Maven is  Dependency and Build Management.

Please check the pom.xml file various dependencies used for this application.

by using terminal mvn clean install for including testcase or mvn clean install 

mvn clean install -Dmaven.test.skip=true for skipping test.

- **Containerization**- Docker.

Install Docker on your machine.

If you want to pull the docker image for this application use

docker pull pannemj/timetracker:1

TO create a Docker image you have to use docker build command by Dockerfile.

docker build -t pannemj/timetracker:1 .

to run created Docker image with different port use -P with docker run.
and use -d for background process.

docker run -d -p "127.0.0.1:8085":8085 pannemj/timetracker:1

To check running container logs use below command.

docker logs (container id).

Finally access the UI by using below link.    

- **Home page** : http://localhost:8085/

- **Resources**: https://docs.docker.com/





