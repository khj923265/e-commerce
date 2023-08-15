FROM openjdk:17-ea-11-jdk-slim
VOLUME /tmp
COPY build/libs/catalog-service-1.0.jar CatalogService.jar
ENV USE_PROFILE prod

ENTRYPOINT ["java", "-Dspring.profiles.active=${USE_PROFILE}", "-jar", "CatalogService.jar"]