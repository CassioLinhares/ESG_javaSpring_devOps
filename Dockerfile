FROM eclipse-temurin:21-jdk AS build
WORKDIR /workspace
COPY .mvn .mvn
COPY mvnw pom.xml ./
RUN chmod +x mvnw && ./mvnw -B -q dependency:go-offline
COPY src src
RUN ./mvnw -B -q package -DskipTests

FROM eclipse-temurin:21-jre
LABEL authors="cassio linhares"
WORKDIR /app
RUN groupadd --system spring && useradd --system --gid spring spring
COPY --from=build /workspace/target/spring_backend_esg-0.0.1-SNAPSHOT.jar app.jar
USER spring
VOLUME /tmp
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
