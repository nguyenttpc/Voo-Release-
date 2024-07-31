FROM openjdk:17
EXPOSE 8080
COPY target/VooTreeVeeVuu-0.0.1-SNAPSHOT.jar VooTreeVeeVuu-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/VooTreeVeeVuu-0.0.1-SNAPSHOT.jar"]