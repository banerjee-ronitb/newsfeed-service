FROM openjdk:11
ADD target/newsfeed-service-0.0.1-SNAPSHOT.jar newsfeed-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "newsfeed-service-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080