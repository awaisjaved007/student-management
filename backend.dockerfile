FROM openjdk:8u131-jre-alpine

WORKDIR /app

ARG artifactid
ARG version

ENV artifact ${artifactid}-${version}.jar

COPY ./target/${artifact} app.jar

RUN chmod +x /app/app.jar

CMD ["/usr/bin/java", "-jar", "app.jar"]

EXPOSE 8080


