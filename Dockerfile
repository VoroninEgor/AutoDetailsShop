FROM eclipse-temurin:21-jre-alpine
WORKDIR /opt/app
EXPOSE 8080
COPY /target/auto-shop-0.0.1.jar /opt/app/*.jar
ENTRYPOINT ["java", "-jar", "/opt/app/*.jar"]
