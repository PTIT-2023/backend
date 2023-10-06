FROM openjdk:11

COPY build\libs/AOManager-0.0.1-SNAPSHOT.jar /app.jar

CMD ["java", "-jar", "/app.jar"]

EXPOSE 8080
ENV PORT 8080
