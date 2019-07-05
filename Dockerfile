FROM openjdk:8

ARG APP_NAME
ARG APP_VERSION

COPY target/${APP_NAME}-${APP_VERSION}.jar /app/app.jar
CMD java -jar /app/app.jar
