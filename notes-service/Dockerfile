FROM openjdk:17-jdk-alphine
WORKDIR /app
COPY target/notes-service.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","app.jar"]
