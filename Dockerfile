FROM openjdk:17-oracle

WORKDIR /app

COPY ./target/cathayunited-0.0.1-SNAPSHOT.jar /app

CMD ["java", "-jar", "cathayunited-0.0.1-SNAPSHOT.jar"]