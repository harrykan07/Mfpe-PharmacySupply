FROM openjdk:11
EXPOSE 8082
ADD target/pharmacy-supply.jar pharmacy-supply.jar
ENTRYPOINT ["java","-jar","/pharmacy-supply.jar"]
