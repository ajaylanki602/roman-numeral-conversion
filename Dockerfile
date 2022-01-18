FROM openjdk:8-jdk-alpine
COPY target/roman-numeral-conversion-0.0.1-SNAPSHOT.jar roman-numeral-conversion.jar
ENTRYPOINT ["java","-jar","/roman-numeral-conversion.jar"]