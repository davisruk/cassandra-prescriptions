FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ARG JAR_FOLDER="./target"
ARG JAR_FILE="app.jar"
ARG HTTP_PROXY="http://gateway.zscalertwo.net:9480"
ARG HTTPS_PROXY="http://gateway.zscalertwo.net:9480"
RUN apk update && apk upgrade && add --update bind-tools 
ADD /$JAR_FOLDER/$JAR_FILE app.jar
RUN sh -c 'touch /app.jar'
#ENV JAVA_OPTS=""
#ENV APP_OPTS=""
#ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar $APP_OPTS" ]