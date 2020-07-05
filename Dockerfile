FROM openjdk:8

COPY target/Java*.jar /colaboradorApi.jar

CMD ["java", "-jar", "/colaboradorApi.jar"]