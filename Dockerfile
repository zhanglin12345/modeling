FROM openjdk:11-slim
COPY /build/libs/modeling-0.0.1-SNAPSHOT.jar /app/modeling-0.0.1-SNAPSHOT.jar
EXPOSE 8080
CMD java -jar /app/modeling-0.0.1-SNAPSHOT.jar

#docker build . -t modeling-image
#docker run -t -p 8080:8080 --name modeling-app modeling-image