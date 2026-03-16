FROM tomcat:11.0.18
COPY target/StudentManagementSystem.war /usr/local/tomcat/webapps/
EXPOSE 8081
CMD ["catalina.sh", "run"]
