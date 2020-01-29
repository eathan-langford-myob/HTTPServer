FROM penjdk:14-alpine
COPY ./build/libs/Hello-World-1.0-SNAPSHOT-all.jar /usr/app/
WORKDIR /usr/app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "Hello-World-1.0-SNAPSHOT-all.jar"]