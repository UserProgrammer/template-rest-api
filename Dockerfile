FROM openjdk:8

COPY target/${APP_NAME}-${APP_VERSION}-SNAPSHOT.jar /app/app.jar
CMD java -jar /app/app.jar
