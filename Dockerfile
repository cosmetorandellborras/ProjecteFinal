FROM tomcat:latest

LABEL maintainer="Cosme Torandell"

ADD ./target/ProjecteFinal.war /usr/local/tomcat/webapps/

EXPOSE 8080

CMD ["catalina.sh", "run"]

