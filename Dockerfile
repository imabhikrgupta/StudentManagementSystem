FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app
COPY . .

RUN mvn clean package -DskipTests

FROM tomcat:11.0.18
COPY --from=build /app/target/*.war /usr/local/tomcat/webapps/ROOT.war
