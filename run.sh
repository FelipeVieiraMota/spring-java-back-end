#!/bin/bash

# Bash for Linux

clear &&
JAVA_HOME=$(dirname $( readlink -f $(which java) )) &&
JAVA_HOME=$(realpath "$JAVA_HOME"/../) &&
export JAVA_HOME &&
mvn clean &&
./mvnw install &&
docker build -t myorg/myapp . &&
docker run -p 3000:8080  myorg/myapp

