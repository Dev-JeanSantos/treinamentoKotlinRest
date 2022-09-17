FROM adoptopenjdk/openjdk11
EXPOSE 8080
ADD /target/treinamentoKotlin-0.0.1-SNAPSHOT.jar treinamentoKotlin.jar
ENTRYPOINT ["java", "$JAVA_OPTS -XX:+UseContainerSupport", "-Xmx300m -Xss512k -XX:CICompilerCount=2", "-Dserver.port=$PORT", "-Dspring.profiles.active=prod", "-jar", "treinamentoKotlin.jar"]

