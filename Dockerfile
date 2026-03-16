FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app
COPY . .

WORKDIR /app/StudentManagementSystem
RUN mvn clean package

FROM tomcat:11.0.18
COPY --from=build /app/StudentManagementSystem/target/*.war /usr/local/tomcat/webapps/
