FROM java:8
EXPOSE 8081
ADD /target/micfin-0.0.1-SNAPSHOT.jar micfin-services.jar
ENTRYPOINT ["java","-jar","micfin-services.jar"]