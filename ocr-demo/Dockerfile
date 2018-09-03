FROM anapsix/alpine-java:8_jdk
ADD target/*.jar /app.jar
ADD src/main/sh/entrypoint.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh
ENV JAVA_OPTS="-Xmx512m -Xms128m -XX:MinHeapDeltaBytes=65536 -Dcom.sun.management.jmxremote=true -Dcom.sun.management.jmxremote.port=11601 -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false"
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]