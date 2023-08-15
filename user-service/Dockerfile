FROM openjdk:17-ea-11-jdk-slim
VOLUME /tmp
COPY build/libs/user-service-1.0.jar UserService.jar
ENV USE_PROFILE prod

ENTRYPOINT ["java", "-Dspring.profiles.active=${USE_PROFILE}", "-jar", "UserService.jar"]