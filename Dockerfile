FROM openjdk:8
COPY target/Library-API-0.0.1-SNAPSHOT.jar app.jar
ARG SPRING_DATASOURCE_URL
EXPOSE 8081
ENTRYPOINT ["java","-Dspring.datasource.url=${SPRING_DATASOURCE_URL}" ,"-jar", "app.jar"]