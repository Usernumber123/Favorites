FROM alpine:latest
EXPOSE 8090
RUN apk add openjdk11
ENV TZ=Europe/Moscow
VOLUME /tmp
ADD target/preferences-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]