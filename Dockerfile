FROM ibmjava:8-jre

COPY target/wso2-example-1.0.0-SNAPSHOT.jar /
ENTRYPOINT ["/usr/bin/java", "-jar", "/wso2-example-1.0.0-SNAPSHOT.jar"]

EXPOSE 8080
