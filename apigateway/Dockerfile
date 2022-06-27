FROM adoptopenjdk/openjdk11:alpine-jre
ADD target/apigateway-0.0.1-SNAPSHOT.jar apigateway.jar
ENTRYPOINT ["java","-Dspring.profiles.active=docker", "-jar","apigateway.jar"]
EXPOSE 8083