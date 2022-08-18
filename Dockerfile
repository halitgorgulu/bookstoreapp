FROM openjdk:11
EXPOSE 8080
ADD target/bookstoreapp.jar bookstoreapp.jar
ENTRYPOINT ["java","-jar","/bookstoreapp.jar"]