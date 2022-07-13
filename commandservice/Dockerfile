FROM adoptopenjdk/openjdk11:alpine-jre
ADD commandservice/target/commandservice-0.0.1-SNAPSHOT-exec.jar commandservice.jar
ENTRYPOINT ["java","-Dspring.profiles.active=docker", "-jar","commandservice.jar"]
EXPOSE 8081