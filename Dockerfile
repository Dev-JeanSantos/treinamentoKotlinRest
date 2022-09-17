FROM adoptopenjdk/openjdk11
EXPOSE 8080
ADD /target/treinamentoKotlin-0.0.1-SNAPSHOT.jar treinamentoKotlin.jar
ENTRYPOINT ["java", "-jar", "treinamentoKotlin.jar"]
