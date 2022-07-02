FROM adoptopenjdk/openjdk14:alpine-jre
ADD target/serviceregistry-0.0.1-SNAPSHOT.jar serviceregistry.jar
ENTRYPOINT ["java","-Dspring.profiles.active=docker", "-jar","serviceregistry.jar"]
EXPOSE 8761


