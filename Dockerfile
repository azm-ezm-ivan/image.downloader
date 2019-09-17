FROM java:8-jdk-alpine
MAINTAINER  Ivan Vasilyeu <azm_ezm_ivan@mail.ru>
COPY ./target/imagedownloader-1.0.jar usr/app/
WORKDIR usr/app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "imagedownloader-1.0.jar"]