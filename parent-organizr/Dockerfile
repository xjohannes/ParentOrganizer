# syntax=docker/dockerfile:1
FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS
COPY target/parent-organizr-0.0.1-SNAPSHOT.jar parentorganizrbackend.jar
EXPOSE 8080
# ENTRYPOINT exec java $JAVA_OPTS -jar parentorganizrbackend.jar
# For Spring-Boot project, use the entrypoint below to reduce Tomcat startup time.
# ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar parentorganizrbackend.jar
 # Entrypoint as exec form with spring-boot tomcat optimization
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/parentorganizrbackend.jar"]
