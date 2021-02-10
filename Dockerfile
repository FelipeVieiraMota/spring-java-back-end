# ****************************************************************
# Commands to build and run ( Execute it in the shell )
# ****************************************************************
#   JAVA_HOME=$(dirname $( readlink -f $(which java) )) &&
#   JAVA_HOME=$(realpath "$JAVA_HOME"/../) &&
#   export JAVA_HOME &&
#   mvn clean &&
#   ./mvnw install &&
#   docker build -t myorg/myapp . &&
#   docker run -p 3000:8080 myorg/myapp
#*****************************************************************

# In this case we are using .war insted of .jar
# docker build -t myorg/myapp . (Never uncomment this line)


FROM openjdk:12-alpine
COPY target/*.war /backoffice.war
CMD ["java", "-jar", "/backoffice.war"]
ENTRYPOINT [ "sh", "-c", "java -jar backoffice.war" ]
