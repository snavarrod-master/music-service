FROM openjdk:17
ENV APP_FILE app.jar
ENV APP_HOME /app
RUN mkdir $APP_HOME
EXPOSE 8443
RUN ls -la /
COPY ./build/libs/musicquizz-0.0.1-SNAPSHOT.jar /$APP_HOME/$APP_FILE
WORKDIR $APP_HOME
ENTRYPOINT ["sh", "-c"]
CMD ["exec java -jar $APP_FILE"]
