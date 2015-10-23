FROM tomcat:8-jre7

MAINTAINER chris.ru@gmail.com

ADD ./target/people-restful-1.0.0-SNAPSHOT.war /usr/local/tomcat/webapps/

CMD ["./bin/catalina.sh", "run"]