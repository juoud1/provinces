# This is a Dockerfile for province microservice application

# Use an official Java 8 runtime as parent image
FROM maven:3.6.1-jdk-8-alpine

VOLUME /tmp

# Set maintainer email id
MAINTAINER devsolibill@gmail.com

# Set the working directory contents into the container at /app
WORKDIR /app

# Copy the current directory contents into the container at /app
ADD . /app

# Build and create jar using maven command
RUN mvn package -DskipTests=true -Ddir=app

# Copy the current directory contents into the container at /app
ADD target/psolibill.jar provinces-psolibill.jar

# Make port 8988 available to the world outside this container
EXPOSE 8988

# Define environment variable
ENV JAVA_OPTS=""

# Run psolibill.jar when the container launches
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar provinces-psolibill.jar"]