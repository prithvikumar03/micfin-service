FROM maven:3.6.2-jdk-8-slim

EXPOSE 9000
VOLUME /root/.m2

WORKDIR /usr/src/micfin

COPY . /usr/src/micfin/

CMD ["mvn", "spring-boot:run"]