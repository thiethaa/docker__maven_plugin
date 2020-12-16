FROM openjdk:latest
ARG JAR_FILE
ADD ${JAR_FILE} /docker-maven-plugin.jar
ENTRYPOINT ["java","-jar","docker-maven-plugin.jar"]