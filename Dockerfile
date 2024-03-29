FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/book_author-0.0.1-SNAPSHOT.jar book_author.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","book_author.jar"]