FROM openjdk:8
EXPOSE 8087
ADD target/stage.jar stage.jar
ENTRYPOINT ["java","-jar","/stage.jar"]