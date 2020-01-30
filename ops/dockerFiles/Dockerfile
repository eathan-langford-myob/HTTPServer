FROM openjdk:14-alpine AS building
COPY ./build/libs/Hello-World-1.0-SNAPSHOT.jar /usr/app/
WORKDIR /usr/app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "Hello-World-1.0-SNAPSHOT.jar"]
RUN echo 'Australia/Melbourne' > /etc/timezone