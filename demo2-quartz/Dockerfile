FROM openjdk:8-jre-alpine
WORKDIR /home
ADD ./demo2-quartz/target/demo2-quartz-1.0-SNAPSHOT.jar app.jar
EXPOSE 23333
ENTRYPOINT ["java","-XX:+UnlockExperimentalVMOptions","-XX:+UseCGroupMemoryLimitForHeap","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=test","-jar","app.jar"]