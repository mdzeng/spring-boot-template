FROM java:8-jre
EXPOSE 8080
CMD java -jar spring-boot-template.jar
COPY build/libs/spring-boot-template.jar .