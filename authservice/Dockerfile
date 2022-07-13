FROM adoptopenjdk/openjdk11:alpine-jre
ADD authservice/target/authservice-0.0.1-SNAPSHOT-exec.jar auth.jar
ENTRYPOINT ["java","-Dspring.profiles.active=docker", "-jar","auth.jar"]
EXPOSE 8083