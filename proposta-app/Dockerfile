FROM openjdk:17

#copia meu jar gerado e joga pro container com mesmo nome
COPY target/proposta-app-0.0.1-SNAPSHOT.jar proposta-app-0.0.1-SNAPSHOT.jar

#esse comando é reposavel por subir nosso arquivo jar
ENTRYPOINT [ "java","-jar","proposta-app-0.0.1-SNAPSHOT.jar" ]




#docker build -t proposta-app . (para criar a imagem)