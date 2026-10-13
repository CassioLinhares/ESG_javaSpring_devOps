FROM eclipse-temurin:21-jre
LABEL authors="cassio linhares"
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=target/spring_backend_esg-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]