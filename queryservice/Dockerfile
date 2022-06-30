FROM adoptopenjdk/openjdk11:alpine-jre
ADD queryservice/target/queryservice-0.0.1-SNAPSHOT-exec.jar queryservice.jar
ENTRYPOINT ["java","-Dspring.profiles.active=docker", "-jar","queryservice.jar"]
EXPOSE 8082